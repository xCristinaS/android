package cristina.ejercicio31_recyclerview_listheterogenea;

import android.os.Parcelable;

/**
 * Created by Cristina on 15/01/2016.
 */
public abstract class TipoGenerico implements Parcelable {

    protected static final int TYPE_CURSO = 0;
    protected static final int TYPE_ALUMNO = 1;

    public abstract int getType();
}
