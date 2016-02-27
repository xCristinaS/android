package c.trabajo_fct.clases_modelo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Cristina on 27/02/2016.
 */
public class Profesor implements Parcelable {

    private int id;
    private String nombre, contra;

    public Profesor(){}

    public Profesor(String nombre, String contra) {
        id = -1;
        this.nombre = nombre;
        this.contra = contra;
    }

    public Profesor(int id, String nombre, String contra) {
        this.id = id;
        this.nombre = nombre;
        this.contra = contra;
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

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nombre);
        dest.writeString(this.contra);
    }

    protected Profesor(Parcel in) {
        this.id = in.readInt();
        this.nombre = in.readString();
        this.contra = in.readString();
    }

    public static final Parcelable.Creator<Profesor> CREATOR = new Parcelable.Creator<Profesor>() {
        public Profesor createFromParcel(Parcel source) {
            return new Profesor(source);
        }

        public Profesor[] newArray(int size) {
            return new Profesor[size];
        }
    };
}
