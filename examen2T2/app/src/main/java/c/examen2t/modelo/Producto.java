package c.examen2t.modelo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Cristina on 04/03/2016.
 */
public class Producto implements Parcelable {

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeFloat(this.cantidad);
        dest.writeString(this.nombre);
        dest.writeString(this.unidad);
    }

    protected Producto(Parcel in) {
        this.id = in.readInt();
        this.cantidad = in.readFloat();
        this.nombre = in.readString();
        this.unidad = in.readString();
    }

    public static final Creator<Producto> CREATOR = new Creator<Producto>() {
        public Producto createFromParcel(Parcel source) {
            return new Producto(source);
        }

        public Producto[] newArray(int size) {
            return new Producto[size];
        }
    };
}
