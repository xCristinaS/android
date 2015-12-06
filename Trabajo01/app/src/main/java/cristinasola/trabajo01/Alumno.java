package cristinasola.trabajo01;

/**
 * Created by Cristina on 06/12/2015.
 */
public class Alumno {

    private String nombre, telefono, apellidos, direccion;

    public Alumno(String nombre, String telefono, String apellidos, String direccion){
        this.nombre = nombre;
        this.telefono = telefono;
        this.apellidos = apellidos;
        this.direccion = direccion;
    }

    public String getNombre(){
        return nombre;
    }

    public String getTelefono(){
        return telefono;
    }

    public String getApellidos(){
        return apellidos;
    }

    public String getDireccion() {
        return direccion;
    }
}
