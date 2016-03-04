package c.examen2t.bdd;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Cristina on 04/03/2016.
 */
public class MyContentProvider extends ContentProvider{
    // Constantes generales.
    // Autoridad. Debe ser similar al valor de android:authority de la etiqueta
    // <provider> en el manifiesto.
    private static final String AUTHORITY = "c.examen2t.bdd.provider";
    private static final Uri CONTENT_URI_BASE = Uri.parse("content://" + AUTHORITY); // Uri base de acceso al proveedor.

    // Constantes para la entidad Alumnos.
    private static final String BASE_PATH_PRODUCTO = "producto"; // Segmento path.
    public static final Uri CONTENT_URI_PRODUCTOS = Uri.withAppendedPath(CONTENT_URI_BASE, BASE_PATH_PRODUCTO); // Uri pública de acceso a alumnos.
    private static final String MIME_TYPE_PRODUCTOS = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd.c.examen2t.bdd.provider.productos"; // Tipo MIME alumnos.
    private static final String MIME_ITEM_TYPE_PRODUCTOS = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd.c.examen2t.bdd.producto"; // Tipo MIME alumno.

    // Constantes para tipos de Uris (deben tener todos un valor diferente).
    private static final int URI_TYPE_PRODUCTOS_LIST = 10; // Tipo para alumnos.
    private static final int URI_TYPE_PRODUCTO_ID = 20; // Tipo para alumno.

    // Se crea el validador de Uris, al que se le añaden todas los tipos de uris
    // considerados válidos.
    private static final UriMatcher validadorURIs = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        validadorURIs.addURI(AUTHORITY, BASE_PATH_PRODUCTO, URI_TYPE_PRODUCTOS_LIST);
        validadorURIs.addURI(AUTHORITY, BASE_PATH_PRODUCTO + "/#", URI_TYPE_PRODUCTO_ID);
    }

    // Retorna el tipo de uri recibida.
    @Override
    public String getType(Uri uri) {
        // Dependiendo del tipo de uri solicitada.
        int tipoURI = validadorURIs.match(uri);
        switch (tipoURI) {
            case URI_TYPE_PRODUCTOS_LIST:
                return MIME_TYPE_PRODUCTOS;
            case URI_TYPE_PRODUCTO_ID:
                return MIME_ITEM_TYPE_PRODUCTOS;
            default:
                throw new IllegalArgumentException("URI desconocida: " + uri);
        }
    }

    // Variables miembro.
    // private SQLiteDatabase bd;
    private SQLiteHelper helper;

    // Retorna si ha ido bien.
    @Override
    public boolean onCreate() {
        // Se crea el helper para el acceso a la bd.
        helper = SQLiteHelper.getInstance(getContext(), BddContract.BD_NOMBRE, null, BddContract.BD_VERSION);
        return true;
    }

    // Retorna el cursor con el resultado de la consulta. Recibe los parámetros
    // indicados en el método query del ContentResolver.
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // Se abre la base de datos.
        SQLiteDatabase bd = helper.getReadableDatabase();
        // Se crea un constructor de consultas.
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        // Dependiendo de la uri solicitada.
        int tipoURI = validadorURIs.match(uri);
        switch (tipoURI) {
            case URI_TYPE_PRODUCTOS_LIST:
                // Se compueba si el llamador ha solicitado una columna que no
                // existe.
                checkColumns(BddContract.Producto.TODOS, projection);
                // Se establece la tabla para la consulta.
                builder.setTables(BddContract.Producto.TABLA);
                break;
            case URI_TYPE_PRODUCTO_ID:
                // Se compueba si el llamador ha solicitado una columna que no
                // existe.
                checkColumns(BddContract.Producto.TODOS, projection);
                // Se establece la tabla para la consulta.
                builder.setTables(BddContract.Producto.TABLA);
                // Se agrega al where la selección de ese alumno.
                builder.appendWhere(BddContract.Producto.ID + " = " + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("URI desconocida: " + uri);
        }
        // Se realiza la consulta.
        Cursor cursor = builder.query(bd, projection, selection, selectionArgs, null, null, sortOrder);
        // Se notifica a los escuchadores del content provider.
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    // Retorna el número de registros eliminados. Recibe los parámetros recibido
    // por el método delete del ContentResolver.
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int filasBorradas;
        // Se inicializa la selección.
        String where = selection;
        // Se obtiene la base de datos.
        SQLiteDatabase bd = helper.getWritableDatabase();
        // Dependiendo de la uri solicitada.
        int tipoURI = validadorURIs.match(uri);
        switch (tipoURI) {
            case URI_TYPE_PRODUCTOS_LIST:
                // Se realiza el borrado.
                filasBorradas = bd.delete(BddContract.Producto.TABLA, where, selectionArgs);
                break;
            case URI_TYPE_PRODUCTO_ID:
                // Se agrega al where la selección de ese alumno.
                where = BddContract.Producto.ID + "=" + uri.getLastPathSegment() + (TextUtils.isEmpty(selection) ? "" : " and " + where);
                // Se realiza el borrado.
                filasBorradas = bd.delete(BddContract.Producto.TABLA, where, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("URI desconocida: " + uri);
        }
        // Se notifica de los cambios a todos los listener.
        if (filasBorradas > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return filasBorradas;
    }

    // Retorna la uri del nuevo registro. Recibe los parámetros recibido por el
    // método insert del ContentResolver.
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id;
        // Se obtiene la base de datos.
        SQLiteDatabase bd = helper.getWritableDatabase();
        // Dependiendo de la uri solicitada.
        int tipoURI = validadorURIs.match(uri);
        switch (tipoURI) {
            case URI_TYPE_PRODUCTOS_LIST:
                id = bd.insert(BddContract.Producto.TABLA, null, values);
                break;
            default:
                throw new IllegalArgumentException("URI desconocida: " + uri);
        }
        // Se notifica a los escuchadores del content provider.
        getContext().getContentResolver().notifyChange(uri, null);
        // Se retorna la URI del registro insertado.
        return ContentUris.withAppendedId(uri, id);
    }

    // Retorna el numero de registros actualizados. Recibe los parámetros
    // recibidos por el método update del ContentResolver.
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int filasActualizadas;
        // Se inicializa la parte del where.
        String where = selection;
        // Se obtiene la base de datos.
        SQLiteDatabase bd = helper.getWritableDatabase();
        // Dependiendo del tipo de uri solicitada.
        int tipoURI = validadorURIs.match(uri);
        switch (tipoURI) {
            case URI_TYPE_PRODUCTOS_LIST:
                filasActualizadas = bd.update(BddContract.Producto.TABLA, values, where, selectionArgs);
                break;
            case URI_TYPE_PRODUCTO_ID:
                // Se agrega al where la selección de ese alumno.
                where = BddContract.Producto.ID + "=" + uri.getLastPathSegment() + (TextUtils.isEmpty(selection) ? "" : " and " + where);
                filasActualizadas = bd.update(BddContract.Producto.TABLA, values, where, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("URI desconocida: " + uri);
        }
        // Se notifica a los listeners.
        if (filasActualizadas > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        // Se retorna el número de registros actualizados.
        return filasActualizadas;
    }

    // Comprueba si todas las columnas están entre las disponibles.
    private void checkColumns(String[] disponibles, String[] columnas) {
        if (columnas != null) {
            HashSet<String> columnasSolicitadas = new HashSet<>(Arrays.asList(columnas));
            HashSet<String> columnasDisponibles = new HashSet<>(Arrays.asList(disponibles));
            // Si hay alguna solicitada no disponible se lanza excepción.
            if (!columnasDisponibles.containsAll(columnasSolicitadas)) {
                throw new IllegalArgumentException("Se ha solicitado un campo desconocido");
            }
        }
    }
}
