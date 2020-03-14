package multimedia.rubio.miguel.persistenciasql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PersonaSQLiteHelper extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE persona (id INT AUTOINCREMENT, nombre TEXT, apellido TEXT)";

    public PersonaSQLiteHelper(Context context, String nomb, SQLiteDatabase.CursorFactory factory, int vers){
        super(context,nomb, factory, vers);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS magic");
        db.execSQL(sqlCreate);
    }
}
