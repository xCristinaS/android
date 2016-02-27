package c.ejercicio47_mycontentprovider.recicladoDelEjercicio37;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Cristina on 28/01/2016.
 */
public class Alumno implements Parcelable{

    public Integer id;
    public String foto = "http://lorempixel.com/400/200/";
    public String nombre = "sin esp";
    public String curso = "sin esp";
    public String telefono = "sin esp";
    public String direccion = "sin esp";
    public Integer edad = -5;
    public Boolean repetidor = true;

    public Alumno (){

    }

    public Alumno(int id){
        this.id = id;
    }

    public Alumno(Integer id, String foto, String nombre, String curso, String telefono, String direccion, Integer edad, Boolean repetidor) {
        this.id = id;
        this.foto = foto;
        this.nombre = nombre;
        this.curso = curso;
        this.telefono = telefono;
        this.direccion = direccion;
        this.edad = edad;
        this.repetidor = repetidor;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", foto='" + foto + '\'' +
                ", nombre='" + nombre + '\'' +
                ", curso='" + curso + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                ", edad=" + edad +
                ", repetidor=" + repetidor +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Boolean getRepetidor() {
        return repetidor;
    }

    public void setRepetidor(Boolean repetidor) {
        this.repetidor = repetidor;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.foto);
        dest.writeString(this.nombre);
        dest.writeString(this.curso);
        dest.writeString(this.telefono);
        dest.writeString(this.direccion);
        dest.writeValue(this.edad);
        dest.writeValue(this.repetidor);
    }

    protected Alumno(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.foto = in.readString();
        this.nombre = in.readString();
        this.curso = in.readString();
        this.telefono = in.readString();
        this.direccion = in.readString();
        this.edad = (Integer) in.readValue(Integer.class.getClassLoader());
        this.repetidor = (Boolean) in.readValue(Boolean.class.getClassLoader());
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
