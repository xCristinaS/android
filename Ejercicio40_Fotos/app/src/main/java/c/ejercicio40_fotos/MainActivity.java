package c.ejercicio40_fotos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int RC_CAPTURAR_FOTO = 1;
    private static final String PREF_PATH_FOTO = "prefFoto";
    private static final int RC_RECORTAR_FOTO = 2;
    private static String sNombreArchivo, sPathFotoOriginal, sPathFotoEscalada;
    ImageView imgFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();

        // Se lee de las preferencias el path del archivo con la foto escalada y privada
        // (si fuera una base de datos, leeríamos del registro correspondiente.
        SharedPreferences preferencias = getSharedPreferences(getString(R.string.app_name),
                MODE_PRIVATE);
        String pathFoto = preferencias.getString(PREF_PATH_FOTO, "");
        if (!TextUtils.isEmpty(pathFoto)) {
            // Se muestra en el ImageView.
            imgFoto.setImageURI(Uri.fromFile(new File(pathFoto)));
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hayCamara())
                    capturarFoto();
            }
        });
    }

    private void initViews() {
        imgFoto = (ImageView) findViewById(R.id.imgFoto);
    }

    // Guarda en preferencias el path de archivo mostrado en el ImageView.
    private void guardarEnPreferencias(String path) {
        // Se almacena en las preferencias el path del archivo con la foto escalada y privada
        SharedPreferences preferencias = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString(PREF_PATH_FOTO, path);
        editor.apply();
    }

    private boolean hayCamara() {
        return getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    // Envía un intent implícito para la captura de una foto.
    // Recibe el nombre que debe tomar el archivo con la foto escalada y guardada en privado.
    private void capturarFoto() {
        // Se guarda el nombre para uso posterior.
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Si hay alguna actividad que sepa realizar la acción.
        if (i.resolveActivity(getPackageManager()) != null) {
            // Se crea el archivo para la foto en el directorio público (true).
            // Se obtiene la fecha y hora actual.
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
            String nombre = "IMG_" + timestamp + "_" + ".jpg";
            File fotoFile = crearArchivoFoto(nombre, true);
            if (fotoFile != null) {
                // Se guarda el path del archivo para cuando se haya hecho la captura.
                sPathFotoOriginal = fotoFile.getAbsolutePath();
                sNombreArchivo = nombre;
                // Se añade como extra del intent la uri donde debe guardarse.
                i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fotoFile));
                startActivityForResult(i, RC_CAPTURAR_FOTO);
            }
        }
    }

    private File crearArchivoFoto(String nombre, boolean publico) {
        // Se obtiene el directorio en el que almacenarlo.
        File directorio;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            if (publico) {
                // En el directorio público para imágenes del almacenamiento externo.
                directorio = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            } else {
                directorio = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            }
        } else {
            // En almacenamiento interno.
            directorio = getFilesDir();
        }
        // Su no existe el directorio, se crea.
        if (directorio != null && !directorio.exists()) {
            if (!directorio.mkdirs()) {
                Log.d(getString(R.string.app_name), "error al crear el directorio");
                return null;
            }
        }
        // Se crea un archivo con ese nombre y la extensión jpg en ese
        // directorio.
        File archivo = null;
        if (directorio != null) {
            archivo = new File(directorio.getPath() + File.separator + nombre);
            Log.d(getString(R.string.app_name), archivo.getAbsolutePath());
        }
        // Se retorna el archivo creado.
        return archivo;
    }

    // Envía un intent implícito para recortar la imagen. Recibe el path de la foto a recortar.
    // Si no es posible recortar, se llama a cargarImagenEscalada().
    private void recortarImagen(String pathFoto) {
        // Se guarda el nombre para uso posterior.
        Intent i = new Intent("com.android.camera.action.CROP");
        i.setDataAndType(Uri.fromFile(new File(pathFoto)), "image/*");
        // Si hay alguna actividad que sepa realizar la acción de recortar.
        if (i.resolveActivity(getPackageManager()) != null) {
            i.putExtra("crop", "true");
            // Ratio.
            i.putExtra("aspectX", getResources().getDimensionPixelSize(R.dimen.anchoFoto));
            i.putExtra("aspectY", getResources().getDimensionPixelSize(R.dimen.altoFoto));
            // Tamaño de salida.
            i.putExtra("outputX", getResources().getDimensionPixelSize(R.dimen.anchoFoto));
            i.putExtra("outputY", getResources().getDimensionPixelSize(R.dimen.altoFoto));
            i.putExtra("return-data", true);
            // Inicio la actividad esperando el resultado.
            startActivityForResult(i, RC_RECORTAR_FOTO);
        } else {
            // Si no se puede recortar, se escala la imagen y se muestra.
            mostrarFoto();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == RC_CAPTURAR_FOTO) {
            // Tengo que cargar y mostrar la foto escalada.
            recortarImagen(sPathFotoOriginal);
        } else if (resultCode == RESULT_OK && requestCode == RC_RECORTAR_FOTO){
            mostrarFoto();
        }
    }

    private void mostrarFoto() {
        // Se obtiene el tamaño de la vista de destino.
        int anchoImageView = imgFoto.getWidth();
        int altoImageView = imgFoto.getHeight();
        // Se obtiene el tamaño de la imagen.
        BitmapFactory.Options opciones = new BitmapFactory.Options();
        opciones.inJustDecodeBounds = true; // Solo para cálculo.
        BitmapFactory.decodeFile(sPathFotoOriginal, opciones);
        int anchoFoto = opciones.outWidth;
        int altoFoto = opciones.outHeight;
        // Se obtiene el factor de escalado para la imagen.
        int factorEscalado = Math.min(anchoFoto / anchoImageView, altoFoto / altoImageView);
        // Se escala la imagen con dicho factor de escalado.
        opciones.inJustDecodeBounds = false; // Se escalará.
        opciones.inSampleSize = factorEscalado;
        Bitmap foto = BitmapFactory.decodeFile(sPathFotoOriginal, opciones);
        File archivo = crearArchivoFoto(sNombreArchivo, false);
        if (archivo != null) {
            if (guardarBitmapEnArchivo(foto, archivo)) {
                // Se almacena el path de la foto a mostrar en el ImageView.
                sPathFotoEscalada = archivo.getAbsolutePath();
                // Se muestra la foto en el ImageView.
                imgFoto.setImageBitmap(foto);
                guardarEnPreferencias(sPathFotoEscalada);
            }
        }
    }

    // Guarda el bitamp de la foto en un archivo. Retorna si ha ido bien.
    private boolean guardarBitmapEnArchivo(Bitmap bitmapFoto, File archivo) {
        try {
            FileOutputStream flujoSalida = new FileOutputStream(archivo);
            bitmapFoto.compress(Bitmap.CompressFormat.JPEG, 100, flujoSalida);
            flujoSalida.flush();
            flujoSalida.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /*
    // Primera version, solo captura la miniatura.

    private void capturarFoto() {
        // Se crea el intent implícito para realizar la acción.
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Si hay alguna actividad que sepa realizar la acción.
        if (i.resolveActivity(getPackageManager()) != null)
            // Se envía el intent esperando respuesta.
            startActivityForResult(i, RC_CAPTURAR_FOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == RC_CAPTURAR_FOTO) {
            // Se obtiene la miniatura desde el extra del intent de resultado.
            Bundle extras = data.getExtras();
            Bitmap miniatura = (Bitmap) extras.get("data");
            // Se muestra en el ImageView.
            imgFoto.setImageBitmap(miniatura);
        }
    }
    */
}
