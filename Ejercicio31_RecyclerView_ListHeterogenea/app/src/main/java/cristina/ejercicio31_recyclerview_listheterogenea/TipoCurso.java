package cristina.ejercicio31_recyclerview_listheterogenea;

import android.os.Parcel;

/**
 * Created by Cristina on 15/01/2016.
 */
public class TipoCurso extends TipoGenerico {

    private String nombreCurso;
    
    public TipoCurso(String nombreCurso){
        this.nombreCurso = nombreCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    @Override
    public int getType() {
        return TYPE_CURSO;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nombreCurso);
    }

    protected TipoCurso(Parcel in) {
        this.nombreCurso = in.readString();
    }

    public static final Creator<TipoCurso> CREATOR = new Creator<TipoCurso>() {
        public TipoCurso createFromParcel(Parcel source) {
            return new TipoCurso(source);
        }

        public TipoCurso[] newArray(int size) {
            return new TipoCurso[size];
        }
    };
}
