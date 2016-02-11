package cris.ejercicio38_palette;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import it.sephiroth.android.library.picasso.Callback;
import it.sephiroth.android.library.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private static final String URL_FOTO = "http://lorempixel.com/400/200/sports/";
    private ImageView imgFoto;
    private Button btnColores, btnImagen;
    private Palette mPaleta;
    private Toolbar toolbar;
    private int numImg = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imgFoto = (ImageView) findViewById(R.id.imgFoto);
        Picasso.with(this).load(URL_FOTO + "/" + numImg).into(imgFoto);

        btnColores = (Button) findViewById(R.id.btnCambiarColores);
        btnColores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerPaleta();
            }
        });

        btnImagen = (Button) findViewById(R.id.btnCambiarImg);
        btnImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numImg == 10)
                    numImg = 1;

                cargarFoto();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void cargarFoto() {
        Picasso.with(this).load(URL_FOTO + "/" + numImg).into(imgFoto, new Callback() {
            @Override
            public void onSuccess() {
                obtenerPaleta();
                numImg++;
            }

            @Override
            public void onError() {
            }
        });
    }

    private void obtenerPaleta() {
        Bitmap bitmap = ((BitmapDrawable) imgFoto.getDrawable()).getBitmap();
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                // La paleta de 16 colores ya ha sido generada asíncronamente.
                mPaleta = palette;
                if (mPaleta.getDarkVibrantSwatch() != null)
                    setStatusBarcolor(getWindow(), mPaleta.getDarkVibrantSwatch().getRgb());
                if (mPaleta.getLightVibrantSwatch() != null) {
                    toolbar.setBackgroundColor(mPaleta.getLightVibrantSwatch().getRgb());
                    toolbar.setTitleTextColor(mPaleta.getLightVibrantSwatch().getBodyTextColor());
                }
            }
        });
    }

    // Establece el color de fondo de la status bar (API > 21).
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private static void setStatusBarcolor(Window window, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(color);
        }
    }
}
