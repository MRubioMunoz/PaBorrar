package multimedia.rubio.miguel.persistenciasql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button create;
    private Button read;
    private Button update;
    private Button delete;

    private EditText nombre;
    private EditText apellido;

    private ListView listView;

    private List<String> magics = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PersonaSQLiteHelper personaSQLiteHelper = new PersonaSQLiteHelper(this,"DBMagic",null,1);
        final SQLiteDatabase db = personaSQLiteHelper.getWritableDatabase();

        create = findViewById(R.id.create);
        read = findViewById(R.id.read);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);

        nombre = findViewById(R.id.editText1);
        apellido = findViewById(R.id.editText2);

        listView = findViewById(R.id.listview);

        ListAdapter listAdapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,magics);
        listView.setAdapter(listAdapter);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues nuevoRegistro = new ContentValues();
                nuevoRegistro.put("campo1", nombre.toString());
                nuevoRegistro.put("campo2", apellido.toString());
                db.insert("persona", null, nuevoRegistro);
                Toast.makeText(getApplicationContext(),"Create",Toast.LENGTH_SHORT).show();

            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor miCursor = db.rawQuery("SELECT campo1,campo2 FROM magic WHERE id = ?",null);

                if(miCursor.moveToFirst()){
                    do{
                        Persona persona = new Persona();
                        persona.setNombre(miCursor.getString(miCursor.getColumnIndex("campo1")));
                        persona.setApellido(miCursor.getString(miCursor.getColumnIndex("campo2")));

                        magics.add(persona.getNombre() + " " + persona.getApellido());
                    }while (miCursor.moveToNext());
                }
                miCursor.close();

                Toast.makeText(getApplicationContext(),"Read",Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues valores = new ContentValues();
                valores.put("nombre","personaNueva");
                db.update("persona",valores,"nombre",null);
                Toast.makeText(getApplicationContext(),"Update",Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"Delete",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
