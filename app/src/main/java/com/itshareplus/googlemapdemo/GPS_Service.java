package com.itshareplus.googlemapdemo;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;

/**
 * Created by AZeaage on 3/27/2017.
 */

public class GPS_Service extends Service implements LocationListener {
    private LocationListener listener;
    private LocationManager manager;
    private final Context context;
    boolean isGPSEnabled=false;
    boolean isNetworkEnable=false;
    boolean canGetLocation = false;

    double latitude;
    double longtitude;

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES=10;
    private static final long MIN_TIME_BW_UPDATES=1000*6*1;

    protected LocationManager locationManager;
    

    public GPS_Service(Context context) {
        this.context = context;
     //   getLocation();
    }

  /*  private Location getLocation() {
      try{  locationManager=(LocationManager)context.getSystemService(LOCATION_SERVICE);
        isGPSEnabled=locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetworkEnable=locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if(isGPSEnabled &&isNetworkEnable){


        }else
        {
            this.canGetLocation=true;
            if(isNetworkEnable){

            }
        }}catch (Exception e){
          System.out.println(e.getMessage());
      }

    }
*/
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        listener=new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Intent i =new Intent("location_update");
                i.putExtra("coordinates",location.getLongitude()+" "+location.getLatitude());
                sendBroadcast(i);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent i = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);

            }
        };

        manager=(LocationManager)getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        //noinspection MissingPermission
        manager.requestLocationUpdates(manager.GPS_PROVIDER,3000,0,listener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(manager !=null){
            manager.removeUpdates(listener);
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
