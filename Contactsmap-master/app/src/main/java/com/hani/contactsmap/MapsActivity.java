package com.hani.contactsmap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG_CONTACTS_LIST = "list";
    private static final String TAG_LATITUDE_LIST = "latitude_list";
    private static final String TAG_LONGITUDE_LIST= "longitude_list";
    private static final String TAG_NAME = "name";
    private static final String TAG_EMAIL = "email";
    private static final String TAG_PHONE = "phone";
    private static final String TAG_PHONE_OFFICE = "officePhone";

    private GoogleMap mMap;
    ArrayList<HashMap<String, String>> contacts;
    ArrayList<String> latitude_list;
    ArrayList<String> longitude_list, name_list, email_list, phone_no_list, office_no_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent in = getParent().getIntent();

        contacts = (ArrayList<HashMap<String, String>>) in.getSerializableExtra(TAG_CONTACTS_LIST);


        String latitude,longitude;
        latitude_list = new ArrayList<String>();
        name_list = new ArrayList<String>();
        email_list = new ArrayList<String>();
        phone_no_list = new ArrayList<String>();
        office_no_list = new ArrayList<String>();
        longitude_list = new ArrayList<String>();
        HashMap<String,String> hash;
        System.out.println(contacts);

        for(int i=0; i< contacts.size(); i++)
        {

            latitude_list.add(contacts.get(i).get("latitude"));
            longitude_list.add(contacts.get(i).get("longitude"));
            name_list.add(contacts.get(i).get("name"));
            email_list.add(contacts.get(i).get("email"));
            phone_no_list.add(contacts.get(i).get("phone"));
            office_no_list.add(contacts.get(i).get("officePhone"));

        }

        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        if (mMap == null) {
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap()
    {
        if(latitude_list.size()>0) {
            Toast.makeText(getBaseContext(), "Launching", Toast.LENGTH_LONG).show();
            for (int i = 0; i < latitude_list.size(); i++)
                mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(latitude_list.get(i)), Double.parseDouble(longitude_list.get(i)))).title(name_list.get(i) + "\n" ).snippet("Phone number :- " + phone_no_list.get(i) + "\n" + "Email ID :- " + email_list.get(i) + "\n" + "Office Phone number :- " + office_no_list.get(i)));

        }
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override

            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(getApplicationContext(), SingleContactActivity.class);
                intent.putExtra(TAG_NAME,marker.getTitle());
                intent.putExtra(TAG_EMAIL,marker.getSnippet());

                startActivity(intent);


            }
        });
        Toast.makeText(getBaseContext(), "Map activity launched", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }



}
