package multimedia.rubio.miguel.geopo02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView salida = findViewById(R.id.salida);
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

        salida.append(location.getAltitude() + " : Altitud");
        salida.append(location.getLatitude() + " : Latitud");
        salida.append(location.getLongitude() + " : Longitud");
    }
}
