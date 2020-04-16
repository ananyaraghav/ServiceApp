package com.example.serviceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView res;
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.searchAddresseditText);
        res=findViewById(R.id.result);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            editText.setText("MY Lat: "+location.getLatitude() + "  Lng: "+location.getLongitude());

                            ArrayList<LatLng> locations;
                            ArrayList<LatLng> desiredLocations;
                            TextView result;
                            locations = new ArrayList();
                            desiredLocations = new ArrayList();
                            locations.add(new LatLng(28.39552, 77.30526));
                            locations.add(new LatLng(29.39552, 77.30526));
                            locations.add(new LatLng(28.49552, 77.28526));

                            for(int i=0;i<locations.size();i++) {
                                Double newlat = locations.get(i).latitude;
                                Double newlong = locations.get(i).longitude;

                                float distance;
                                distance = getKmFromLatLong(location.getLatitude(), location.getLongitude(), newlat, newlong);

                                if(distance < 0.5){
                                    desiredLocations.add(new LatLng(locations.get(i).latitude, locations.get(i).longitude));
                                }
                            }

                            StringBuffer sb = new StringBuffer();
                            for (int i = 0; i < desiredLocations.size(); i++) {
                                sb.append(desiredLocations.get(i).latitude + "      " + desiredLocations.get(i).longitude + "\n");
                            }

                            res.setText(sb.toString());
                        }
                    }
                });


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
