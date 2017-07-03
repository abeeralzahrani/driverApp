package com.itshareplus.googlemapdemo;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import Modules.DirectionFinder;
import Modules.DirectionFinderListener;
import Modules.Route;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, DirectionFinderListener {

    private GoogleMap mMap;
    private Button btnFindPath;
    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    private ProgressDialog progressDialog;
    private MarkerOptions marker;
    String docNumFromDB;
    GPSTracker gps;
    String Latitude, Longitude,position_str;
    int position;
    String Permission = "";
    Button button;
    String destination = "";
    String Origin;

    // ********************************************************************
    public DirectionFinder directionFinder;
    public LatLng driverLocation;
    public LatLng customerLocation = null;
    public LatLng cameraLocation;

    private LocationManager locationManager;
  //  private LocationListener listener;
    int invoicePosition;
    public ArrayList<String> pickupLocations = new ArrayList<String>();
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

          SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                  .findFragmentById(R.id.map);
          mapFragment.getMapAsync(this);
        button = (Button) findViewById(R.id.setLocationButton);
        LinearLayout linear = (LinearLayout) findViewById(R.id.linearLayout);
        title= (TextView) findViewById(R.id.title);
        mMap = null;
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
          // To retrieve object in second Activity
        Permission = (String) getIntent().getSerializableExtra("Permission");
        Latitude = (String) getIntent().getSerializableExtra("Latitude");
        Longitude = (String) getIntent().getSerializableExtra("Longitude");
        double lat,lng;
        switch (Permission) {
            case "setCustomerLocation":
                title.setVisibility(View.GONE);
                linear.setVisibility(View.GONE);
                position_str = (String) getIntent().getSerializableExtra("position");
                position = Integer.parseInt(position_str);
                if(!Latitude.isEmpty()&&!Longitude.isEmpty()){
                    lat = Double.parseDouble(Latitude);
                    lng = Double.parseDouble(Longitude);
                    System.out.println(lat + "  location substring " + lng);
                    customerLocation = new LatLng(lat, lng);
                }
                docNumFromDB = (String) getIntent().getSerializableExtra("DocNum");

                cameraLocation=getCurrentLocation();
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!customerLocation.equals(null)) {
                            setLocationOfCustomer();
                        } else
                            Toast.makeText(getBaseContext(), "you should deticating the location of the customer ", Toast.LENGTH_LONG).show();
                    }
                });
                break;
            case "findPath":
                int currentInvoiceIndex=search_invoices_Fragment.sortCount+1;
                int totalNumberOfInvoices = search_invoices_Fragment.sortIndex+1;
                System.out.println(currentInvoiceIndex+" current Inndex "+totalNumberOfInvoices+"/n");
               title.setText("Invoice Number "+currentInvoiceIndex+"/"+totalNumberOfInvoices);
                Origin = (String) getIntent().getSerializableExtra("Origin");
                System.out.println("origin loc MA :"+Origin);
                sendRequest(Origin,Latitude+","+Longitude);
                int index_subSting =Origin.indexOf(',');
                lat= Double.parseDouble(Origin.substring(0,index_subSting));
                lng = Double.parseDouble(Origin.substring(index_subSting+1));
                driverLocation=new LatLng(lat,lng);
                cameraLocation=driverLocation;
                button.setText("Close Invoice ");
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent close_Intent = new Intent(getApplicationContext(),Close_Invoice.class);
                        startActivity(close_Intent);
                       finish();

                    }
                });
                break;}

          gps = new GPSTracker(MapsActivity.this);

      }
    public LatLng getCurrentLocation() {
        gps = new GPSTracker(this);

        double latitude,longitude;
        LatLng location= null;
        if (gps.canGetLocation()) {
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            location=new LatLng(latitude,longitude);
            if(location == new LatLng(0,0)){
                getCurrentLocation();
            }
        } else {
            gps.showSettingsAlert();
        }
        return location;
    }

    private void sendRequest(String origin , String destination) {

        // destination= etDestination.getText().toString();

        if (origin.isEmpty()) {
            Toast.makeText(this, "Please enter origin address!" + origin, Toast.LENGTH_SHORT).show();
            return ;
        }
        if (destination.equals("")) {
            Toast.makeText(this, "Please click on the location for the customer to find the path", Toast.LENGTH_SHORT).show();
            return ;
        }
     //   mMap.setTrafficEnabled(true);
        try {
            System.out.println(destination + "  customer location");
            System.out.println(origin + "  driver location");
            new DirectionFinder(this, origin, destination).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //LatLng hcmus = new LatLng(24.703732, 46.703508);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cameraLocation, 15));
     /*   originMarkers.add(mMap.addMarker(new MarkerOptions()
                .title("driver location")
                .position(driverLocation)));
*/
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        if (Permission.equals("setCustomerLocation")) {
            //set the location of the customer when he don't setting it
            mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

                @Override
                public void onMapClick(LatLng point) {
                    // TODO Auto-generated method stub
                    mMap.clear();
                    //center = new LatLng(lat,lng);// PARAMETER point;
                    customerLocation = point;
                    System.out.println("locationnnnn of click &&&&&&&&&&&" + customerLocation);
                    originMarkers.add(mMap.addMarker(new MarkerOptions()
                            .title("customer location ")
                            .position(customerLocation)));
                    //  setLocationOfCustomer();
                    //set the location for the customer

                }
            });
        }
    }

    protected void setLocationOfCustomer() {
        //  marker = new MarkerOptions().position(customerLocation).title("your location ");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        // mMap.addMarker(marker);
        // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(customerLocation, 15));
        destination = customerLocation.latitude + "," + customerLocation.longitude;
        background b = new background(getBaseContext());
        String lat = customerLocation.latitude + "";
        String lon = customerLocation.longitude + "";
     //   System.out.println("lat " + lat + " lon " + lon);
        try {
            String result = b.execute("SaveGPSLocation", docNumFromDB, lat, lon).get();
            System.out.println(result + " set location response ");
            if (!result.contains("Error")) {
                Toast.makeText(getBaseContext(), "The location is assigned successfully", Toast.LENGTH_LONG).show();
                search_invoices_Fragment.salesOrderse.get(position).setLatitude(lat);
                search_invoices_Fragment.salesOrderse.get(position).setLongitude(lon);
                finish();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
       // locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this.listener);
    }

    @Override
    public void onDirectionFinderStart() {
        progressDialog = ProgressDialog.show(this, "Please wait.",
                "Finding direction..!", true);

        if (originMarkers != null) {
            for (Marker marker : originMarkers) {
                marker.remove();
            }
        }

        if (destinationMarkers != null) {
            for (Marker marker : destinationMarkers) {
                marker.remove();
            }
        }

        if (polylinePaths != null) {
            for (Polyline polyline:polylinePaths ) {
                polyline.remove();
            }
        }
    }

    @Override
    public String onDirectionFinderSuccess(List<Route> routes) {
        progressDialog.dismiss();
        polylinePaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();

        for (Route route : routes) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startLocation, 16));
            ((TextView) findViewById(R.id.tvDuration)).setText(route.duration.text);
            ((TextView) findViewById(R.id.tvDistance)).setText(route.distance.text);
            originMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.start_blue))
                    .title(route.startAddress)
                    .position(route.startLocation)));
            destinationMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.end_green))
                    .title(route.endAddress)
                    .position(route.endLocation)));

            PolylineOptions polylineOptions = new PolylineOptions().
                    geodesic(true).
                    color(Color.BLUE).
                    width(10);

            mMap.setTrafficEnabled(false);

            for (int i = 0; i < route.points.size(); i++)
                polylineOptions.add(route.points.get(i));

            polylinePaths.add(mMap.addPolyline(polylineOptions));
            //  mMap.setTrafficEnabled(true);
            return route.distance.text;
        }
        return null;
    }
}