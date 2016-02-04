package cristina.ejercicio37_stetho_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Cristina on 01/02/2016.
 */
public class InstitutoSQLiteHelper extends SQLiteOpenHelper {

    private static InstitutoSQLiteHelper helper;

    private static final String SQL_CREATE_ALUMNOS = String.format("CREATE  TABLE %s ('%s' INTEGER PRIMARY KEY  AUTOINCREMENT , '%s' VARCHAR, '%s' VARCHAR, '%s' VARCHAR, '%s' VARCHAR," +
            " '%s' VARCHAR, '%s' INTEGER, '%s' BOOL)",Instituto.Alumno.TABLA, Instituto.Alumno.ID, Instituto.Alumno.FOTO, Instituto.Alumno.NOMBRE, Instituto.Alumno.CURSO,
            Instituto.Alumno.TELEFONO, Instituto.Alumno.DIRECCION, Instituto.Alumno.EDAD, Instituto.Alumno.REPETIDOR);


    private InstitutoSQLiteHelper(Context contexto, String nombreBD, CursorFactory factory, int versionBD){
        super(contexto, nombreBD, factory, versionBD);
    }

    public static InstitutoSQLiteHelper getInstance(Context contexto, String nombreBD, CursorFactory factory, int versionBD){
        if (helper == null)
            helper = new InstitutoSQLiteHelper(contexto, nombreBD, factory, versionBD);

        return helper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ALUMNOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Instituto.Alumno.TABLA);
        db.execSQL(SQL_CREATE_ALUMNOS);
    }
}
