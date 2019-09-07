package website.timrobinson.opencvtutorial;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import java.util.List;

public class Map extends AppCompatActivity implements PermissionsListener, View.OnClickListener {
    private MapView mapView;
    private PermissionsManager permissionsManager;
    private MapboxMap mapboxMap;

    private View bg_topview;
    private View bg_botView;
    private LinearLayout logoView;
    private ImageView floatButton;


    private LinearLayout ly1,ly2,ly3;

    private LinearLayout ly_l1,ly_l2,ly_l3;
    private ImageView iv_l1,iv_l2,iv_l3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "pk.eyJ1IjoibG9jaWFsIiwiYSI6ImNqcXk2NTlkczAwOTQ0OG52OWZmYWYwOWYifQ.CX4oYv51xMu9Phmw7dUvDQ.CX4oYv51xMu9Phmw7dUvDQ");
        setContentView(R.layout.activity_map);
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        bg_topview = findViewById(R.id.topview);
        bg_botView = findViewById(R.id.botview);
        logoView = findViewById(R.id.logoView);
        floatButton = findViewById(R.id.floatButton);
        floatButton.setOnClickListener(this);

        bg_topview.bringToFront();
        bg_botView.bringToFront();
        logoView.bringToFront();

        mapView.getMapAsync(new OnMapReadyCallback() {
            @SuppressLint("WrongConstant")
            @Override
            public void onMapReady(@NonNull final MapboxMap mapboxMap) {
                mapboxMap.setStyle("mapbox://styles/locial/ck071rdl50bzb1cpfnrqiozz3", new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {

                        Map.this.mapboxMap = mapboxMap;
                        enableLocationComponent();
// Map is set up and the style has loaded. Now you can add data or make other map adjustments
                    }
                });
            }
        });
    }
    private void enableLocationComponent() {
        // Check if permissions are enabled and if not request
        if (PermissionsManager.areLocationPermissionsGranted(this)) {

            // Get an instance of the component
            LocationComponent locationComponent = mapboxMap.getLocationComponent();

            // Activate
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
            locationComponent.activateLocationComponent(getApplicationContext(),mapboxMap.getStyle());

            // Enable to make component visible
            locationComponent.setLocationComponentEnabled(true);

            // Set the component's camera mode
            locationComponent.setCameraMode(CameraMode.TRACKING);

            // Set the component's render mode
            locationComponent.setRenderMode(RenderMode.COMPASS);
        } else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {

    }

    @Override
    public void onPermissionResult(boolean granted) {

        if (granted) {
            enableLocationComponent();
        } else {
            //Toast.makeText(this, R.string.user_location_permission_not_granted, Toast.LENGTH_LONG).show();
            finish();
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.floatButton :

                //Toast.makeText(getApplicationContext(),"Ağaç dikim sayfasını aç",0).show();

                showPlantTreeDialog();

                break;

            case R.id.layout_1 :


                break;

            case R.id.layout_2 :


                break;

            case R.id.layout_3 :


                break;

        }

    }

    private void showPlantTreeDialog() {

        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_ptree);

        ly1 = dialog.findViewById(R.id.layout_1);
        ly2 = dialog.findViewById(R.id.layout_2);
        ly3 = dialog.findViewById(R.id.layout_3);

        ly_l1 = dialog.findViewById(R.id.ly_l1);
        ly_l2 = dialog.findViewById(R.id.ly_l2);
        ly_l3 = dialog.findViewById(R.id.ly_l3);

        iv_l1 = dialog.findViewById(R.id.iv_l1);
        iv_l2 = dialog.findViewById(R.id.iv_l2);
        iv_l3 = dialog.findViewById(R.id.iv_l3);

        ly_l2.setBackgroundColor(Color.RED);
        ly_l3.setBackgroundColor(Color.RED);

        iv_l2.setImageResource(R.drawable.redcross);
        iv_l3.setImageResource(R.drawable.redcross);


        ly1.setOnClickListener(this);
        ly2.setOnClickListener(this);
        ly3.setOnClickListener(this);




        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        dialog.show();

    }
}
