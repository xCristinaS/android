package c.examen2t.bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Cristina on 04/03/2016.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    private static SQLiteHelper helper;

    private static final String SQL_CREATE_TABLE_PRODUCTO = String.format("create table %s (%s integer primary key autoincrement, %s varchar (50) not null, %s float, %s varchar(20));",
            BddContract.Producto.TABLA, BddContract.Producto.ID, BddContract.Producto.NOMBRE, BddContract.Producto.CANTIDAD, BddContract.Producto.UNIDAD);


    private SQLiteHelper(Context contexto, String nombreBD, SQLiteDatabase.CursorFactory factory, int versionBD){
        super(contexto, nombreBD, factory, versionBD);
    }

    public static SQLiteHelper getInstance(Context contexto, String nombreBD, SQLiteDatabase.CursorFactory factory, int versionBD){
        if (helper == null)
            helper = new SQLiteHelper(contexto, nombreBD, factory, versionBD);

        return helper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_PRODUCTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BddContract.Producto.TABLA);
        db.execSQL(SQL_CREATE_TABLE_PRODUCTO);
    }
}
