package c.ejercicio46_firebasealumnos.modelo_datos;

/**
 * Created by Cristina on 22/02/2016.
 */
public class GrupoAlumno {

    private Grupo grupo;

    public GrupoAlumno(){

    }

    public GrupoAlumno(Grupo grupo){
        this.grupo = grupo;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
}
