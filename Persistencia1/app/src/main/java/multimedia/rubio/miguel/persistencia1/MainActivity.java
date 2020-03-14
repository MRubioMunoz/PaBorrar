package multimedia.rubio.miguel.persistencia1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText texto1;
    private EditText texto2;

    private TextView textView1;
    private TextView textView2;

    private Button guardar;
    private Button recuperar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto1 = findViewById(R.id.texto1);
        texto2 = findViewById(R.id.texto2);

        textView1 = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

        guardar = findViewById(R.id.guardar);
        recuperar = findViewById(R.id.recuperar);

        final SharedPreferences preferences = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        final SharedPreferences.Editor editor = preferences.edit();

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("campo1", texto1.getText().toString());
                editor.putString("campo2", texto2.getText().toString());
                editor.commit();
            }
        });

        recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView1.setText(preferences.getString("campo1", "por defecto"));
                textView2.setText(preferences.getString("campo2", "por defecto"));
            }
        });

    }
}
