package com.samples.location.getcoordinates;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GetCoordinatesActivity extends AppCompatActivity {

    private LocationManager manager;
    private TextView text;

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            printLocation(location);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {
            printLocation(null);
        }
    };

    private void printLocation(Location loc) {
        if (loc != null)
        {
            text.setText("Longtitude:\t" + loc.getLongitude() +
                    "\nLatitude:\t" + loc.getLatitude());
        }
        else {
            text.setText("Location unavailable");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_coordinates);

        text = (TextView)findViewById(R.id.text);
        manager = (LocationManager)getSystemService(LOCATION_SERVICE);

        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

        Location loc = manager.getLastKnownLocation(
                LocationManager.GPS_PROVIDER);
        printLocation(loc);
    }
}
