package c.trabajo_fct.modelos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Cristina on 27/02/2016.
 */
public class Empresa implements Parcelable {

    private int id;
    private String nombre, direccion, telefono, foto;

    public Empresa(){}

    public Empresa(String nombre, String direccion, String telefono, String foto) {
        id = -1;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.foto = foto;
    }

    public Empresa(int id, String nombre, String direccion, String telefono, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nombre);
        dest.writeString(this.direccion);
        dest.writeString(this.telefono);
        dest.writeString(this.foto);
    }

    protected Empresa(Parcel in) {
        this.id = in.readInt();
        this.nombre = in.readString();
        this.direccion = in.readString();
        this.telefono = in.readString();
        this.foto = in.readString();
    }

    public static final Parcelable.Creator<Empresa> CREATOR = new Parcelable.Creator<Empresa>() {
        public Empresa createFromParcel(Parcel source) {
            return new Empresa(source);
        }

        public Empresa[] newArray(int size) {
            return new Empresa[size];
        }
    };
}
