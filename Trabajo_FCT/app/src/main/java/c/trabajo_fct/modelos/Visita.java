package c.trabajo_fct.modelos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Cristina on 27/02/2016.
 */
public class Visita implements Parcelable {

    private int idAlumno;
    private Date fecha;
    private String comentario;

    public Visita(){}

    public Visita(int idAlumno, Date fecha, String comentario) {
        this.idAlumno = idAlumno;
        this.fecha = fecha;
        this.comentario = comentario;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.idAlumno);
        dest.writeLong(fecha != null ? fecha.getTime() : -1);
        dest.writeString(this.comentario);
    }

    protected Visita(Parcel in) {
        this.idAlumno = in.readInt();
        long tmpFecha = in.readLong();
        this.fecha = tmpFecha == -1 ? null : new Date(tmpFecha);
        this.comentario = in.readString();
    }

    public static final Parcelable.Creator<Visita> CREATOR = new Parcelable.Creator<Visita>() {
        public Visita createFromParcel(Parcel source) {
            return new Visita(source);
        }

        public Visita[] newArray(int size) {
            return new Visita[size];
        }
    };
}
