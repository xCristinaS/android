package cristinasola.ejercicio15_parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xCristina_S on 23/10/2015.
 */
public class Alumno implements Parcelable{

    String nombre, sexo, dni; int edad;

    public Alumno(String nombre, int edad, String dni){
        this.nombre = nombre;
        this.edad = edad;
        this.dni = dni;
    }

    protected Alumno(Parcel in) {
        nombre = in.readString();
        edad = in.readInt();
        dni = in.readString();
        sexo = in.readString();
    }

    public static final Creator<Alumno> CREATOR = new Creator<Alumno>() {
        @Override
        public Alumno createFromParcel(Parcel in) {
            return new Alumno(in);
        }

        @Override
        public Alumno[] newArray(int size) {
            return new Alumno[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeInt(edad);
        dest.writeString(dni);
        dest.writeString(sexo);
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public int getEdad() {
        return edad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
