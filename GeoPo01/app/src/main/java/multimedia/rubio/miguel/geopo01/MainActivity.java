package multimedia.rubio.miguel.geopo01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView salida;
    private LocationManager miGeoLocalizacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        salida = findViewById(R.id.salida);
        miGeoLocalizacion = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            return;
        }

        List<String> misProveedores =  miGeoLocalizacion.getAllProviders();
        for (String miProveedor: misProveedores) {
            LocationProvider info = miGeoLocalizacion.getProvider(miProveedor);
            salida.append("\n" + "Satelite: " + info.requiresSatellite());
            salida.append("\n" + "Red celular: " + info.requiresCell());
            salida.append("\n" + "Red de datos: " + info.requiresNetwork());
            salida.append("\n" + "Costo económico: " + info.hasMonetaryCost());
            salida.append("\n" + "Altitud: " + info.supportsAltitude());
            salida.append("\n" + "Rumbo: " + info.supportsBearing());
            salida.append("\n" + "Velocidad: " + info.supportsSpeed());
        }



    }
}
