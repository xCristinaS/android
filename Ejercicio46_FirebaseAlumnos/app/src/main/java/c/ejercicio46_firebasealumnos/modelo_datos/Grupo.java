package c.ejercicio46_firebasealumnos.modelo_datos;

/**
 * Created by Cristina on 22/02/2016.
 */
public class Grupo {

    private String descripcion;

    public Grupo(){}

    public Grupo(String descripcion){
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
