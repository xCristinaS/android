package cristinasola.ejercicio24_multilistview_contextactionmode;

/**
 * Created by xCristina_S on 29/10/2015.
 */
public class Alumno {
    private String nombre, telefono;
    private int edad;

    public Alumno(String nombre, int edad, String telefono){
        this.nombre = nombre;
        this.edad = edad;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public int getEdad() {
        return edad;
    }
}
