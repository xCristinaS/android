package c.examen2t.modelo;

/**
 * Created by Cristina on 04/03/2016.
 */
public class Producto {

    private int id;
    private float cantidad;
    private String nombre, unidad;

    public Producto() {
    }

    public Producto(int id, float cantidad, String nombre, String unidad) {
        this.id = id;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.unidad = unidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
}
