package c.trabajo_fct.bdd;

import android.provider.BaseColumns;

/**
 * Created by Cristina on 27/02/2016.
 */
public class BddContract {
    public static final String BD_NOMBRE = "fct";
    public static final int BD_VERSION = 1;

    private BddContract(){}

    public static abstract class Alumno implements BaseColumns {
        public static final String TABLA = "alumno";
        public static final String ID = "idAlumno";
        public static final String NOMBRE = "nombre";
        public static final String DIRECCION = "direccion";
        public static final String TELEFONO = "telefono";
        public static final String CURSO = "curso";
        public static final String EDAD = "edad";
        public static final String FOTO = "foto";
        public static final String PROFESOR = "profesor";
        public static final String EMPRESA = "empresa";
        public static final String[] TODOS = new String[]{ID, FOTO, NOMBRE, CURSO, TELEFONO, DIRECCION, EDAD, PROFESOR, EMPRESA};
    }

    public static abstract class Empresa implements BaseColumns {
        public static final String TABLA = "empresa";
        public static final String ID = "idEmpresa";
        public static final String NOMBRE = "nombre";
        public static final String DIRECCION = "direccion";
        public static final String TELEFONO = "telefono";
        public static final String FOTO = "foto";
        public static final String[] TODOS = new String[]{ID, FOTO, NOMBRE, TELEFONO, DIRECCION};
    }

    public static abstract class Profesor implements BaseColumns {
        public static final String TABLA = "profesor";
        public static final String ID = "idProfesor";
        public static final String NOMBRE = "nombre";
        public static final String CONTRA = "contrasenia";
        public static final String[] TODOS = new String[]{ID, NOMBRE, CONTRA};
    }

    public static abstract class Visitas implements BaseColumns {
        public static final String TABLA = "visitas";
        public static final String ID_PROFESOR = "idProfesor";
        public static final String ID_ALUMNO = "idAlumno";
        public static final String FECHA = "fecha";
        public static final String COMENTARIO = "comentario";
        public static final String[] TODOS = new String[]{ID_PROFESOR, ID_ALUMNO, FECHA, COMENTARIO};
    }
}
