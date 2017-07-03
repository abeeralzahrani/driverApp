package com.itshareplus.googlemapdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity_nav extends AppCompatActivity {

    private TextView mTextMessage;
    userSessionManeger session;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Home_Fragment home_fragment= new Home_Fragment();
                    FragmentManager manager=getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.content,home_fragment,home_fragment.getTag()).commit();

                    return true;
                case R.id.navigation_logout:
                    session.logoutUser();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nav);
        Home_Fragment home_fragment= new Home_Fragment();
        FragmentManager manager=getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content,home_fragment,home_fragment.getTag()).commit();
        session =new userSessionManeger(getBaseContext());
        if(session.checkLogin())
            finish();
        //get user data from session
        HashMap<String,String> user = session.getUserDetails();

        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
