package cristinasola.ejercicio22_fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SecundariaActivity extends AppCompatActivity implements UnoFragment.Callback{

    private static final String EXTRA_MSG = "b";

    FragmentManager gestor; String mensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundaria);

        gestor = getSupportFragmentManager();
        mensaje = getIntent().getStringExtra(EXTRA_MSG);
    }

    public static void start(Context contexto, String mensaje){
        Intent intento = new Intent(contexto, SecundariaActivity.class);
        intento.putExtra(EXTRA_MSG, mensaje);
        contexto.startActivity(intento);
    }


    @Override
    public void meHanPulsado(String mensaje) {
        if (findViewById(R.id.flHuecoSec) == null)
            SecundariaActivity.start(SecundariaActivity.this, mensaje);
        else
            cargarFragmento(mensaje, R.id.flHuecoSec);
    }

    private void cargarFragmento(String mensaje, int id){
        FragmentTransaction transaccion = gestor.beginTransaction();
        transaccion.replace(R.id.flHuecoSec, UnoFragment.newInstance(mensaje));
        transaccion.commit();
    }
}
