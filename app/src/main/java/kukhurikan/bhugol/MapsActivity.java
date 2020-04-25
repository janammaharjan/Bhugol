package kukhurikan.bhugol;

import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    SearchView searchView;
    List<Address> addressList = null;
    Geocoder geocoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //Defining SearchView
        searchView = findViewById(R.id.sv_location);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);


        /// SearchView ///
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                String location= searchView.getQuery().toString();
//
//
//                if(location != null || !location.equals(""))
//                {
//                    geocoder = new Geocoder(MapsActivity.this);
//                    try{
//                        setAddress(location); //To Mark the address
//                        Log.d("ADRESS1: "+location, geocoder.getFromLocationName(location,1).toString());
//
//                    } catch (IOException e) {
//                        Log.d("ADRESS2: ", String.valueOf(addressList));
//                        e.printStackTrace();
//                    }
//                    Log.d("ADRESS: ", String.valueOf(addressList));
//
//                }
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });

/////////////////////////////////


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                View view = getCurrentFocus();
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
//         Add a marker in Sydney and move the camera
        LatLng thamel = new LatLng(27.6726531,85.3227311);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(thamel));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(thamel,18));
        testDirection();



//        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(LatLng point) {
//                mMap.addMarker(new MarkerOptions().position(point).title("Marker"));
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(point));
//            }
//        });

    }


    public void testDirection()
    {
        ArrayList<LatLng> locations = new ArrayList<LatLng>();
        ArrayList<Integer> colors = new ArrayList<>();

        colors.clear();
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.BLACK);

        locations.clear();
        locations.add(new LatLng(27.6726531,85.3227311));
        locations.add(new LatLng(27.6679342,85.3232655));
        locations.add(new LatLng(27.6691794,85.3159152));


        for(int i=0;i<locations.size();i++){
            addMarker(locations.get(i));
            if(i<locations.size()-1) {
                new GetPathFromLocation(colors.get(i), locations.get(i), locations.get(i + 1), new DirectionPointListener() {
                    @Override
                    public void onPath(PolylineOptions polyLine) {
                        mMap.addPolyline(polyLine);
                    }
                }).execute();
            }
        }

    }
    public void addMarker(LatLng latLng)
    {
        mMap.addMarker(new MarkerOptions().position(latLng).title(""));
    }

    public void setAddress (String location) throws IOException {

        addressList = geocoder.getFromLocationName(location,10);
        Address address = addressList.get(0);
        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
        mMap.addMarker(new MarkerOptions().position(latLng).title(location));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
        Polyline line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(27.6957883,85.2980021), new LatLng(27.6957883,85.2980021))
                .width(5)
                .color(Color.RED));
    }


}
