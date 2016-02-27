package c.trabajo_fct.modelos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Cristina on 27/02/2016.
 */
public class Alumno implements Parcelable {

    private int id, edad, empresa;
    private String nombre, direccion, telefono, curso, foto;

    public Alumno(){}

    public Alumno(String nombre, String direccion, String telefono, String curso, int edad, String foto, int empresa){
        id = -1;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.curso = curso;
        this.edad = edad;
        this.foto = foto;
        this.empresa = empresa;
    }

    public Alumno(int id, String nombre, String direccion, String telefono, String curso, int edad, String foto, int empresa){
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.curso = curso;
        this.edad = edad;
        this.foto = foto;
        this.empresa = empresa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
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

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


    public int getEmpresa() {
        return empresa;
    }

    public void setEmpresa(int empresa) {
        this.empresa = empresa;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.edad);
        dest.writeInt(this.empresa);
        dest.writeString(this.nombre);
        dest.writeString(this.direccion);
        dest.writeString(this.telefono);
        dest.writeString(this.curso);
        dest.writeString(this.foto);
    }

    protected Alumno(Parcel in) {
        this.id = in.readInt();
        this.edad = in.readInt();
        this.empresa = in.readInt();
        this.nombre = in.readString();
        this.direccion = in.readString();
        this.telefono = in.readString();
        this.curso = in.readString();
        this.foto = in.readString();
    }

    public static final Creator<Alumno> CREATOR = new Creator<Alumno>() {
        public Alumno createFromParcel(Parcel source) {
            return new Alumno(source);
        }

        public Alumno[] newArray(int size) {
            return new Alumno[size];
        }
    };
}
