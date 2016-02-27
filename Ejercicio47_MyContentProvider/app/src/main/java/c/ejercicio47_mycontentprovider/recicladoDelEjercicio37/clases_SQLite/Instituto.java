package c.ejercicio47_mycontentprovider.recicladoDelEjercicio37.clases_SQLite;

import android.provider.BaseColumns;

/**
 * Created by Cristina on 01/02/2016.
 */
public class Instituto {

    public static final String BD_NOMBRE = "instituto";
    public static final int BD_VERSION = 1;

    public static abstract class Alumno implements BaseColumns {
        public static final String TABLA = "alumnos";
        public static final String ID = "idAlumno";
        public static final String FOTO = "foto";
        public static final String NOMBRE = "nombre";
        public static final String CURSO = "curso";
        public static final String TELEFONO = "telefono";
        public static final String DIRECCION = "direccion";
        public static final String EDAD = "edad";
        public static final String REPETIDOR = "repetidor";
        public static final String[] TODOS = new String[]{ID, FOTO, NOMBRE, CURSO, TELEFONO, DIRECCION, EDAD, REPETIDOR};
    }

    private Instituto() {
    }

}
