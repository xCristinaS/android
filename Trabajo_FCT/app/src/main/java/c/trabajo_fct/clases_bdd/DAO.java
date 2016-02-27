package c.trabajo_fct.clases_bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;

import c.trabajo_fct.clases_modelo.Alumno;
import c.trabajo_fct.clases_modelo.Empresa;
import c.trabajo_fct.clases_modelo.Profesor;
import c.trabajo_fct.clases_modelo.Visita;

/**
 * Created by Cristina on 01/02/2016.
 */
public class DAO {

    private SQLiteHelper helper;

    public DAO(Context context){
        helper = SQLiteHelper.getInstance(context, BddContract.BD_NOMBRE, null, BddContract.BD_VERSION);
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
        valores.put(BddContract.Alumno.FOTO, alumno.getFoto());
        valores.put(BddContract.Alumno.NOMBRE, alumno.getNombre());
        valores.put(BddContract.Alumno.CURSO, alumno.getCurso());
        valores.put(BddContract.Alumno.TELEFONO, alumno.getTelefono());
        valores.put(BddContract.Alumno.DIRECCION, alumno.getDireccion());
        valores.put(BddContract.Alumno.EDAD, alumno.getEdad());
        valores.put(BddContract.Alumno.PROFESOR, alumno.getProfesor());
        valores.put(BddContract.Alumno.EMPRESA, alumno.getEmpresa());
        long resultado = bd.insert(BddContract.Alumno.TABLA, null, valores);
        helper.close();
        return resultado;
    }

    public boolean deleteAlumno(int id) {
        SQLiteDatabase bd = helper.getWritableDatabase();
        long resultado = bd.delete(BddContract.Alumno.TABLA, BddContract.Alumno.ID + " = " + id, null);
        helper.close();
        return resultado > 0;
    }

    public boolean updateAlumno(Alumno alumno) {
        SQLiteDatabase bd = helper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(BddContract.Alumno.FOTO, alumno.getFoto());
        valores.put(BddContract.Alumno.NOMBRE, alumno.getNombre());
        valores.put(BddContract.Alumno.CURSO, alumno.getCurso());
        valores.put(BddContract.Alumno.TELEFONO, alumno.getTelefono());
        valores.put(BddContract.Alumno.DIRECCION, alumno.getDireccion());
        valores.put(BddContract.Alumno.EDAD, alumno.getEdad());
        valores.put(BddContract.Alumno.PROFESOR, alumno.getProfesor());
        valores.put(BddContract.Alumno.EMPRESA, alumno.getEmpresa());
        long resultado = bd.update(BddContract.Alumno.TABLA, valores, BddContract.Alumno.ID + " = " + alumno.getId(), null);
        helper.close();
        return resultado > 0;
    }

    public Alumno selectAlumno(int id) {
        SQLiteDatabase bd = helper.getWritableDatabase();
        Cursor cursor = bd.query(true, BddContract.Alumno.TABLA, BddContract.Alumno.TODOS, BddContract.Alumno.ID + " = " + id, null, null, null, null, null);
        Alumno alumno = null;
        if (cursor != null) {
            cursor.moveToFirst();
            alumno = cursorToAlumno (cursor);
        }
        helper.close();
        return alumno;
    }

    public Cursor queryAllAlumnos(SQLiteDatabase bd) {
        return  bd.query(BddContract.Alumno.TABLA, BddContract.Alumno.TODOS, null, null, null, null, BddContract.Alumno.NOMBRE);
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
        alumno.setId(cursorAlumno.getInt(cursorAlumno.getColumnIndexOrThrow(BddContract.Alumno.ID)));
        alumno.setFoto(cursorAlumno.getString(cursorAlumno.getColumnIndexOrThrow(BddContract.Alumno.FOTO)));
        alumno.setNombre(cursorAlumno.getString(cursorAlumno.getColumnIndexOrThrow(BddContract.Alumno.NOMBRE)));
        alumno.setCurso(cursorAlumno.getString(cursorAlumno.getColumnIndexOrThrow(BddContract.Alumno.CURSO)));
        alumno.setTelefono(cursorAlumno.getString(cursorAlumno.getColumnIndexOrThrow(BddContract.Alumno.TELEFONO)));
        alumno.setDireccion(cursorAlumno.getString(cursorAlumno.getColumnIndexOrThrow(BddContract.Alumno.DIRECCION)));
        alumno.setEdad(cursorAlumno.getInt(cursorAlumno.getColumnIndexOrThrow(BddContract.Alumno.EDAD)));
        alumno.setProfesor(cursorAlumno.getInt(cursorAlumno.getColumnIndexOrThrow(BddContract.Alumno.PROFESOR)));
        alumno.setEmpresa(cursorAlumno.getInt(cursorAlumno.getColumnIndexOrThrow(BddContract.Alumno.EMPRESA)));
        return alumno;
    }

    public long insertEmpresa(Empresa empresa) {
        SQLiteDatabase bd = helper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(BddContract.Empresa.FOTO, empresa.getFoto());
        valores.put(BddContract.Empresa.NOMBRE, empresa.getNombre());
        valores.put(BddContract.Empresa.TELEFONO, empresa.getTelefono());
        valores.put(BddContract.Empresa.DIRECCION, empresa.getDireccion());
        long resultado = bd.insert(BddContract.Empresa.TABLA, null, valores);
        helper.close();
        return resultado;
    }

    public boolean deleteEmpresa(int id) {
        SQLiteDatabase bd = helper.getWritableDatabase();
        long resultado = bd.delete(BddContract.Empresa.TABLA, BddContract.Empresa.ID + " = " + id, null);
        helper.close();
        return resultado > 0;
    }

    public boolean updateEmpresa(Empresa empresa) {
        SQLiteDatabase bd = helper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(BddContract.Empresa.FOTO, empresa.getFoto());
        valores.put(BddContract.Empresa.NOMBRE, empresa.getNombre());
        valores.put(BddContract.Empresa.TELEFONO, empresa.getTelefono());
        valores.put(BddContract.Empresa.DIRECCION, empresa.getDireccion());
        long resultado = bd.update(BddContract.Empresa.TABLA, valores, BddContract.Empresa.ID + " = " + empresa.getId(), null);
        helper.close();
        return resultado > 0;
    }

    public Empresa selectEmpresa(int id) {
        SQLiteDatabase bd = helper.getWritableDatabase();
        Cursor cursor = bd.query(true, BddContract.Empresa.TABLA, BddContract.Empresa.TODOS, BddContract.Empresa.ID + " = " + id, null, null, null, null, null);
        Empresa empresa = null;
        if (cursor != null) {
            cursor.moveToFirst();
            empresa = cursorToEmpresa(cursor);
        }
        helper.close();
        return empresa;
    }

    public Cursor queryAllEmpresa(SQLiteDatabase bd) {
        return  bd.query(BddContract.Empresa.TABLA, BddContract.Empresa.TODOS, null, null, null, null, BddContract.Empresa.NOMBRE);
    }

    public ArrayList<Empresa> selectAllEmpresa() {
        SQLiteDatabase bd = helper.getWritableDatabase();
        ArrayList<Empresa> lista = new ArrayList<>();
        Cursor cursor = queryAllEmpresa(bd);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Empresa empresa = cursorToEmpresa(cursor);
            lista.add(empresa);
            cursor.moveToNext();
        }
        cursor.close();
        helper.close();
        return lista;
    }

    public Empresa cursorToEmpresa(Cursor cursorEmpresa) {
        Empresa empresa = new Empresa();
        empresa.setId(cursorEmpresa.getInt(cursorEmpresa.getColumnIndexOrThrow(BddContract.Empresa.ID)));
        empresa.setFoto(cursorEmpresa.getString(cursorEmpresa.getColumnIndexOrThrow(BddContract.Empresa.FOTO)));
        empresa.setNombre(cursorEmpresa.getString(cursorEmpresa.getColumnIndexOrThrow(BddContract.Empresa.NOMBRE)));
        empresa.setTelefono(cursorEmpresa.getString(cursorEmpresa.getColumnIndexOrThrow(BddContract.Empresa.TELEFONO)));
        empresa.setDireccion(cursorEmpresa.getString(cursorEmpresa.getColumnIndexOrThrow(BddContract.Empresa.DIRECCION)));
        return empresa;
    }

    public long insertProfesor(Profesor profesor) {
        SQLiteDatabase bd = helper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(BddContract.Profesor.NOMBRE, profesor.getNombre());
        valores.put(BddContract.Profesor.CONTRA, profesor.getContra());
        long resultado = bd.insert(BddContract.Profesor.TABLA, null, valores);
        helper.close();
        return resultado;
    }

    public boolean deleteProfesor(int id) {
        SQLiteDatabase bd = helper.getWritableDatabase();
        long resultado = bd.delete(BddContract.Profesor.TABLA, BddContract.Profesor.ID + " = " + id, null);
        helper.close();
        return resultado > 0;
    }

    public boolean updateProfesor(Profesor profesor) {
        SQLiteDatabase bd = helper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(BddContract.Profesor.NOMBRE, profesor.getNombre());
        valores.put(BddContract.Profesor.CONTRA, profesor.getContra());
        long resultado = bd.update(BddContract.Profesor.TABLA, valores, BddContract.Profesor.ID + " = " + profesor.getId(), null);
        helper.close();
        return resultado > 0;
    }

    public Profesor selectProfesor(int id) {
        SQLiteDatabase bd = helper.getWritableDatabase();
        Cursor cursor = bd.query(true, BddContract.Profesor.TABLA, BddContract.Profesor.TODOS, BddContract.Profesor.ID + " = " + id, null, null, null, null, null);
        Profesor profesor = null;
        if (cursor != null) {
            cursor.moveToFirst();
            profesor = cursorToProfesor(cursor);
        }
        helper.close();
        return profesor;
    }

    public Cursor queryAllProfesor(SQLiteDatabase bd) {
        return  bd.query(BddContract.Profesor.TABLA, BddContract.Profesor.TODOS, null, null, null, null, BddContract.Profesor.NOMBRE);
    }

    public ArrayList<Profesor> selectAllProfesor() {
        SQLiteDatabase bd = helper.getWritableDatabase();
        ArrayList<Profesor> lista = new ArrayList<>();
        Cursor cursor = queryAllProfesor(bd);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Profesor profesor = cursorToProfesor(cursor);
            lista.add(profesor);
            cursor.moveToNext();
        }
        cursor.close();
        helper.close();
        return lista;
    }

    public Profesor cursorToProfesor(Cursor cursorProfesor) {
        Profesor profesor = new Profesor();
        profesor.setId(cursorProfesor.getInt(cursorProfesor.getColumnIndexOrThrow(BddContract.Profesor.ID)));
        profesor.setNombre(cursorProfesor.getString(cursorProfesor.getColumnIndexOrThrow(BddContract.Empresa.NOMBRE)));
        profesor.setContra(cursorProfesor.getString(cursorProfesor.getColumnIndexOrThrow(BddContract.Profesor.CONTRA)));
        return profesor;
    }

    public long insertVisita(Visita visita) {
        SQLiteDatabase bd = helper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(BddContract.Visitas.ID_PROFESOR, visita.getIdProfesor());
        valores.put(BddContract.Visitas.ID_ALUMNO, visita.getIdAlumno());
        valores.put(BddContract.Visitas.FECHA, visita.getFecha().getTime());
        valores.put(BddContract.Visitas.COMENTARIO, visita.getComentario());
        long resultado = bd.insert(BddContract.Visitas.TABLA, null, valores);
        helper.close();
        return resultado;
    }

    public boolean deleteVisita(int idProfesor, int idAlumno, Date fecha) {
        SQLiteDatabase bd = helper.getWritableDatabase();
        long resultado = bd.delete(BddContract.Visitas.TABLA, String.format("%s = %d, and %s = %d and %s = %d", BddContract.Visitas.ID_PROFESOR, idProfesor, BddContract.Visitas.ID_ALUMNO, idAlumno,
                BddContract.Visitas.FECHA, fecha.getTime()), null);
        helper.close();
        return resultado > 0;
    }

    public boolean updateVisita(Visita visita) {
        SQLiteDatabase bd = helper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(BddContract.Visitas.ID_PROFESOR, visita.getIdProfesor());
        valores.put(BddContract.Visitas.ID_ALUMNO, visita.getIdAlumno());
        valores.put(BddContract.Visitas.FECHA, visita.getFecha().getTime());
        valores.put(BddContract.Visitas.COMENTARIO, visita.getComentario());
        long resultado = bd.update(BddContract.Visitas.TABLA, valores, String.format("%s = %d, and %s = %d and %s = %d", BddContract.Visitas.ID_PROFESOR, visita.getIdProfesor(), BddContract.Visitas.ID_ALUMNO,
                visita.getIdAlumno(), BddContract.Visitas.FECHA, visita.getFecha().getTime()), null);
        helper.close();
        return resultado > 0;
    }

    public Visita selectVisita(int idProfesor, int idAlumno, Date fecha) {
        SQLiteDatabase bd = helper.getWritableDatabase();
        Cursor cursor = bd.query(true, BddContract.Visitas.TABLA, BddContract.Visitas.TODOS, String.format("%s = %d, and %s = %d and %s = %d", BddContract.Visitas.ID_PROFESOR, idProfesor, BddContract.Visitas.ID_ALUMNO,
                idAlumno, BddContract.Visitas.FECHA, fecha.getTime()), null, null, null, null, null);
        Visita visita = null;
        if (cursor != null) {
            cursor.moveToFirst();
            visita = cursorToVisita(cursor);
        }
        helper.close();
        return visita;
    }

    public Cursor queryAllVisitas(SQLiteDatabase bd) {
        return  bd.query(BddContract.Visitas.TABLA, BddContract.Visitas.TODOS, null, null, null, null, BddContract.Visitas.FECHA + "Desc");
    }

    public ArrayList<Visita> selectAllVisitas() {
        SQLiteDatabase bd = helper.getWritableDatabase();
        ArrayList<Visita> lista = new ArrayList<>();
        Cursor cursor = queryAllVisitas(bd);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Visita visita = cursorToVisita(cursor);
            lista.add(visita);
            cursor.moveToNext();
        }
        cursor.close();
        helper.close();
        return lista;
    }

    public Visita cursorToVisita(Cursor cursorVisitas) {
        Visita visita = new Visita();
        visita.setIdProfesor(cursorVisitas.getInt(cursorVisitas.getColumnIndexOrThrow(BddContract.Visitas.ID_PROFESOR)));
        visita.setIdAlumno(cursorVisitas.getInt(cursorVisitas.getColumnIndexOrThrow(BddContract.Visitas.ID_ALUMNO)));
        visita.setFecha(new Date(cursorVisitas.getLong(cursorVisitas.getColumnIndexOrThrow(BddContract.Visitas.FECHA))));
        visita.setComentario(cursorVisitas.getString(cursorVisitas.getColumnIndexOrThrow(BddContract.Visitas.COMENTARIO)));
        return visita;
    }
}
