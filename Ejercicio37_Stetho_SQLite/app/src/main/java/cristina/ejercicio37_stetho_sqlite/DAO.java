package cristina.ejercicio37_stetho_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Cristina on 01/02/2016.
 */
public class DAO {

    private InstitutoSQLiteHelper helper;

    public DAO(Context context){
        helper = InstitutoSQLiteHelper.getInstance(context, "Alumnos", null, Instituto.BD_VERSION);
    }

    public SQLiteDatabase openWritableDatabase() {
        return helper.getWritableDatabase();
    }

    public void closeDatabase() {
        helper.close();
    }

    public long insertAlumno(Alumno alumno) {
        SQLiteDatabase bd = helper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(Instituto.Alumno.FOTO, alumno.getFoto());
        valores.put(Instituto.Alumno.NOMBRE, alumno.getNombre());
        valores.put(Instituto.Alumno.CURSO, alumno.getCurso());
        valores.put(Instituto.Alumno.TELEFONO, alumno.getTelefono());
        valores.put(Instituto.Alumno.DIRECCION, alumno.getDireccion());
        valores.put(Instituto.Alumno.EDAD, alumno.getEdad());
        valores.put(Instituto.Alumno.REPETIDOR, alumno.getRepetidor());
        long resultado = bd.insert(Instituto.Alumno.TABLA, null, valores);
        helper.close();
        return resultado;
    }

    public boolean deleteAlumno(int id) {
        SQLiteDatabase bd = helper.getWritableDatabase();
        long resultado = bd.delete(Instituto.Alumno.TABLA, Instituto.Alumno.ID + " = " + id, null);
        helper.close();
        return resultado > 0;
    }

    public boolean updateAlumno(Alumno alumno) {
        SQLiteDatabase bd = helper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(Instituto.Alumno.FOTO, alumno.getFoto());
        valores.put(Instituto.Alumno.NOMBRE, alumno.getNombre());
        valores.put(Instituto.Alumno.CURSO, alumno.getCurso());
        valores.put(Instituto.Alumno.TELEFONO, alumno.getTelefono());
        valores.put(Instituto.Alumno.DIRECCION, alumno.getDireccion());
        valores.put(Instituto.Alumno.EDAD, alumno.getEdad());
        valores.put(Instituto.Alumno.REPETIDOR, alumno.getRepetidor());
        long resultado = bd.update(Instituto.Alumno.TABLA, valores, Instituto.Alumno.ID + " = " + alumno.getId(), null);
        helper.close();
        return resultado > 0;
    }

    public Alumno selectAlumno(int id) {
        SQLiteDatabase bd = helper.getWritableDatabase();
        Cursor cursor = bd.query(true, Instituto.Alumno.TABLA, Instituto.Alumno.TODOS, Instituto.Alumno.ID + " = " + id, null, null, null, null, null);
        Alumno alumno = null;
        if (cursor != null) {
            cursor.moveToFirst();
            alumno = cursorToAlumno (cursor);
        }
        helper.close();
        return alumno;
    }

    public Cursor queryAllAlumnos(SQLiteDatabase bd) {
        return  bd.query(Instituto.Alumno.TABLA, Instituto.Alumno.TODOS, null, null, null, null, Instituto.Alumno.NOMBRE);
    }

    public ArrayList<Alumno> selectAllAlumnos() {
        SQLiteDatabase bd = helper.getWritableDatabase();
        ArrayList<Alumno> lista = new ArrayList<>();
        Cursor cursor = queryAllAlumnos(bd);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Alumno alumno = cursorToAlumno(cursor);
            lista.add(alumno);
            cursor.moveToNext();
        }
        cursor.close();
        helper.close();
        return lista;
    }

    public Alumno cursorToAlumno(Cursor cursorAlumno) {
        Alumno alumno = new Alumno();
        alumno.setId(cursorAlumno.getInt(cursorAlumno.getColumnIndexOrThrow(Instituto.Alumno.ID)));
        alumno.setFoto(cursorAlumno.getString(cursorAlumno.getColumnIndexOrThrow(Instituto.Alumno.FOTO)));
        alumno.setNombre(cursorAlumno.getString(cursorAlumno.getColumnIndexOrThrow(Instituto.Alumno.NOMBRE)));
        alumno.setCurso(cursorAlumno.getString(cursorAlumno.getColumnIndexOrThrow(Instituto.Alumno.CURSO)));
        alumno.setTelefono(cursorAlumno.getString(cursorAlumno.getColumnIndexOrThrow(Instituto.Alumno.TELEFONO)));
        alumno.setDireccion(cursorAlumno.getString(cursorAlumno.getColumnIndexOrThrow(Instituto.Alumno.DIRECCION)));
        alumno.setEdad(cursorAlumno.getInt(cursorAlumno.getColumnIndexOrThrow(Instituto.Alumno.EDAD)));
        alumno.setRepetidor(cursorAlumno.getString(cursorAlumno.getColumnIndexOrThrow(Instituto.Alumno.REPETIDOR)).equals("true"));
        return alumno;
    }
}
