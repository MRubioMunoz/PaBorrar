package multimedia.rubio.miguel.geopo04;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity  {

    FusedLocationProviderClient miClienteFussed;
    TextView salida;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        salida = findViewById(R.id.salida);
        miClienteFussed = LocationServices .getFusedLocationProviderClient(this);
        miClienteFussed.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                salida.append(location.getAltitude() + " : Altitud");
                salida.append(location.getLatitude() + " : Latitud");
                salida.append(location.getLongitude() + " : Longitud");
            }
        });


    }
}
