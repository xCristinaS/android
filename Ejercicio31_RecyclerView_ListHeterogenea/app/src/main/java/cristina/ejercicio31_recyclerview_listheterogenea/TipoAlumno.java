package cristina.ejercicio31_recyclerview_listheterogenea;

import android.os.Parcel;

/**
 * Created by Cristina on 15/01/2016.
 */
public class TipoAlumno extends TipoGenerico{

    private String nombreAl;

    public TipoAlumno(String nombreAl){
        this.nombreAl = nombreAl;
    }

    @Override
    public int getType() {
        return TYPE_ALUMNO;
    }

    public void setNombreAl(String nombreAl){
        this.nombreAl = nombreAl;
    }

    public String getNombreAl(){
        return  nombreAl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nombreAl);
    }

    protected TipoAlumno(Parcel in) {
        this.nombreAl = in.readString();
    }

    public static final Creator<TipoAlumno> CREATOR = new Creator<TipoAlumno>() {
        public TipoAlumno createFromParcel(Parcel source) {
            return new TipoAlumno(source);
        }

        public TipoAlumno[] newArray(int size) {
            return new TipoAlumno[size];
        }
    };
}
