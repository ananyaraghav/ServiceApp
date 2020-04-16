package com.example.serviceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class ServicesActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);



        Intent intent=getIntent();
        Double prevlat=intent.getDoubleExtra("lat",0);
        Double prevlong=intent.getDoubleExtra("long",0);


        }

        public static float getKmFromLatLong ( Double oldlat, Double oldlong, Double newlat, Double newlong)
        {
            Location loc1 = new Location("oldlat,oldlong");
            loc1.setLatitude(oldlat);
            loc1.setLongitude(oldlong);
            Location loc2 = new Location("newlat,newlong");
            loc2.setLatitude(newlat);
            loc2.setLongitude(newlong);
            float distanceInMeters = loc1.distanceTo(loc2);
            return distanceInMeters / 1000;
        }

    }
