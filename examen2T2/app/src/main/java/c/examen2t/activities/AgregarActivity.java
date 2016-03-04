package c.examen2t.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import c.examen2t.OnFabPressed;
import c.examen2t.R;
import c.examen2t.fragmentos.FragmentoAgregar;
import c.examen2t.modelo.Producto;

public class AgregarActivity extends AppCompatActivity implements FragmentoAgregar.ActividadAgregarListener{

    public static final String EXTRA_PRODUCTO = "producto";
    private Producto producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.flHuecoAgregar);
                if (fragment instanceof OnFabPressed)
                    ((OnFabPressed) fragment).onFabPressed();
            }
        });

        fab.setImageResource(R.drawable.ic_done);

        getSupportFragmentManager().beginTransaction().replace(R.id.flHuecoAgregar, FragmentoAgregar.newInstance()).commit();
    }

    @Override
    public void obtenerProducto(Producto p) {
        producto = p;
        finish();
    }

    public static void start(Activity a, int requestCode){
        Intent intento = new Intent(a, AgregarActivity.class);
        a.startActivityForResult(intento, requestCode);
    }

    @Override
    public void finish() {
        Intent intento2 = new Intent();
        intento2.putExtra(EXTRA_PRODUCTO, producto);
        setResult(RESULT_OK, intento2);
        super.finish();
    }
}
