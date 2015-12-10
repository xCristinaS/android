package cristinasola.trabajo01;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements FragmentoPrincipal.Callback_Principal, FragmentoSecundario.Callback_FragmentoSec {

    private static final int RESULTADO_MAIN = 1;
    private static final String FRAGMENTO_PRINCIPAL = "principal";
    private static final String FRAGMENTO_SECUNDARIO = "secundario";
    public static final int RESULTADO_FRAGMENT_SEC = 2;
    private static final int RESULTADO = 4;
    FragmentManager gestor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BddAlumnos.agregarAlumno(new Alumno("pepe", "pepito", "123", "calle", "email@e.com"));

        gestor = getSupportFragmentManager();
        cargarFragmentoPrincipal();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            eliminarFragmento(FRAGMENTO_SECUNDARIO);
    }

    private void cargarFragmentoPrincipal(){
        FragmentTransaction transaccion = gestor.beginTransaction();
        transaccion.replace(R.id.flHuecoPrincipal, FragmentoPrincipal.newInstance(), FRAGMENTO_PRINCIPAL);
        transaccion.commit();
    }

    private void cargarFragmentoSecundario(int idAlumno){
        FragmentTransaction transaccion = gestor.beginTransaction();
        transaccion.replace(R.id.flHuecoSecundario, FragmentoSecundario.newInstance(idAlumno), FRAGMENTO_SECUNDARIO);
        transaccion.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean r;
        switch (item.getItemId()){
            case R.id.agregar:
                CrearModificarActivity.startForResult(this, RESULTADO_MAIN, -1);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == RESULTADO_FRAGMENT_SEC && data.hasExtra(SecundaryActivity.EXTRA_ID_ALUMNO)){
                FragmentoSecundario fragmento = (FragmentoSecundario) getSupportFragmentManager().findFragmentByTag(FRAGMENTO_SECUNDARIO);
                fragmento.actualizarDatos();
            }
            FragmentoPrincipal fragmento = (FragmentoPrincipal) gestor.findFragmentByTag(FRAGMENTO_PRINCIPAL);
            fragmento.adaptador.notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void cargarFragmentoDetalles(int posicion) {
        if (findViewById(R.id.flHuecoSecundario) != null)
            cargarFragmentoSecundario(posicion);
        else
            SecundaryActivity.startForResult(this, RESULTADO, posicion);
    }

    @Override
    public void editarAlumno(int idAlumno) {
        CrearModificarActivity.startForResult(this, RESULTADO_FRAGMENT_SEC, idAlumno);
    }

    public void eliminarFragmento(String tagFragment){
        if (gestor.findFragmentByTag(tagFragment) != null)
            gestor.beginTransaction().remove(gestor.findFragmentByTag(tagFragment)).commit();
    }

    public void listaAlumnosVacia(){
        eliminarFragmento(FRAGMENTO_SECUNDARIO);
    }
}
