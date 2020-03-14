package multimedia.rubio.miguel.geopo3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView salida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        salida = findViewById(R.id.salida);
        LocationManager miGeoLocalizacion = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            return;
        }

        Criteria criteria = new Criteria();
        criteria.setAltitudeRequired(true);
        criteria.setAccuracy(Criteria.ACCURACY_FINE);

        String miProveedor = miGeoLocalizacion.getBestProvider(criteria,true);
        Location location = miGeoLocalizacion.getLastKnownLocation(miProveedor);

        final double latitud = location.getLatitude();
        final double longitud = location.getLongitude();

        final LocationListener miEscuchador = new LocationListener(){

            @Override
            public void onLocationChanged(Location location) {
             runOnUiThread(new Runnable() {
                 @Override
                 public void run() {
                    salida.setText( "Latitud:" + latitud + " Longitud:" + longitud);
                 }
             });
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        
        miGeoLocalizacion.requestLocationUpdates(miProveedor,100,5, miEscuchador);
    }
}
