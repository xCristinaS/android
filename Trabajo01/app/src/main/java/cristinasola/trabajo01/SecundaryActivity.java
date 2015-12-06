package cristinasola.trabajo01;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class SecundaryActivity extends AppCompatActivity {

    public static String EXTRA_ID_ALUMNO = "idAlumno";
    android.support.v4.app.FragmentManager gestor;
    Intent intento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundary);

        gestor = getSupportFragmentManager();
        intento = getIntent();
        cargarFragmento(R.id.flHuecoSecundario);
    }

    private void cargarFragmento(int id){
        FragmentTransaction transaccion = gestor.beginTransaction();
        transaccion.replace(id, FragmentoSecundario.newInstance(intento.getIntExtra(EXTRA_ID_ALUMNO, 0)));
        transaccion.commit();
    }
}
