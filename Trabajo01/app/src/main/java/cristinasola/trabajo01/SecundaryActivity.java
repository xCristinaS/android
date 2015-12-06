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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fragmento_secundario_detalles, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean r;
        switch (item.getItemId()){
            case R.id.editar:

                r = true;
                break;
            default:
                r = super.onOptionsItemSelected(item);
        }
        return r;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }
}
