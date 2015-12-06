package cristinasola.trabajo01;

import java.util.ArrayList;

/**
 * Created by Cristina on 06/12/2015.
 */
public class BddAlumnos {

    private static ArrayList<Alumno> alumnos = new ArrayList<>();

    public static void agregarAlumno(Alumno alumno){
        alumnos.add(alumno);
    }

    public static void eliminarAlumno(Alumno alumno) {
        alumnos.remove(alumno);
    }

    public static Alumno seleccionarAlumno(int id){
        return alumnos.get(id);
    }

    public static int indiceAlumno(Alumno alumno){
        return alumnos.indexOf(alumno);
    }

    public static ArrayList<Alumno> getAlumnos(){
        return alumnos;
    }
}
