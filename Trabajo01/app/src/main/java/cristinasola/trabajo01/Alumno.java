package cristinasola.trabajo01;

/**
 * Created by Cristina on 06/12/2015.
 */
public class Alumno {

    private String nombre, telefono, apellidos, direccion, email;

    public Alumno(String nombre, String apellidos, String telefono, String direccion, String email){
        this.nombre = nombre;
        this.telefono = telefono;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.email = email;
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

    public String getEmail(){
        return email;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
}
