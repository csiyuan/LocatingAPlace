package sg.edu.rpc346.id19016011.locatingaplace;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3;
    TextView tv1, tv2;
    private GoogleMap map;
    Spinner spn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                LatLng poi_Singapore = new LatLng(1.3521, 103.8198);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Singapore,
                        11));
                UiSettings ui = map.getUiSettings();
                ui.setCompassEnabled(true);
                ui = map.getUiSettings();
                ui.setZoomControlsEnabled(true);

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);
                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }
                LatLng poi_north = new LatLng(1.4418741280630545, 103.79878435681107);
                Marker north = map.addMarker(new
                        MarkerOptions()
                        .position(poi_north)
                        .title("HQ North")
                        .snippet("Block 333, Admiralty Ave 3, 765654\n Operating hours: 10am-5pm\n" +
                                "Tel:65433456\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

                LatLng poi_central = new LatLng(1.3050564722263174, 103.83214475202963);
                Marker central = map.addMarker(new
                        MarkerOptions()
                        .position(poi_central)
                        .title("Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542 \n" +
                                "Operating hours: 11am-8pm\n" +
                                "Tel:67788652\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                LatLng poi_east = new LatLng(1.3491718972986306, 103.93580749621063);
                Marker east = map.addMarker(new
                        MarkerOptions()
                        .position(poi_east)
                        .title("East")
                        .snippet("Block 555, Tampines Ave 3, 287788 \n" +
                                "Operating hours: 9am-5pm\n" +
                                "Tel:66776677\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));


                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        String markerTitle = marker.getTitle();
                        Toast.makeText(MainActivity.this,  markerTitle, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });

            }
        });

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        spn = findViewById(R.id.spinner);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    LatLng poi_north = new LatLng(1.4418741280630545, 103.79878435681107);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_north,
                            15));
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    LatLng poi_central = new LatLng(1.3050564722263174, 103.83214475202963);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_central,
                            15));
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    LatLng poi_east = new LatLng(1.3491718972986306, 103.93580749621063);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_east,
                            15));
                }
            }
        });

        tv1.setText("ABC Pte Ltd");
        tv2.setText("We now have 3 branches. Look below for the \naddress and info");

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        LatLng poi_north = new LatLng(1.4418741280630545, 103.79878435681107);
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_north,
                                15));
                        break;
                    case 1:
                        LatLng poi_central = new LatLng(1.3050564722263174, 103.83214475202963);
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_central,
                                15));
                        break;
                    case 2:
                        LatLng poi_east = new LatLng(1.3491718972986306, 103.93580749621063);
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_east,
                                15));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        


    }
}

