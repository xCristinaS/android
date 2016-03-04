package c.examen2t.bdd;

import android.provider.BaseColumns;

/**
 * Created by Cristina on 04/03/2016.
 */
public class BddContract {
    public static final String BD_NOMBRE = "productos";
    public static final int BD_VERSION = 1;

    private BddContract(){}

    public static abstract class Producto implements BaseColumns {
        public static final String TABLA = "producto";
        public static final String ID = "idProducto";
        public static final String NOMBRE = "nombre";
        public static final String CANTIDAD = "cantidad";
        public static final String UNIDAD = "unidad";
        public static final String[] TODOS = new String[]{ID, NOMBRE, CANTIDAD, UNIDAD};
    }
}
