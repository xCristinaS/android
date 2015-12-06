package cristinasola.trabajo01;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private static final int RESULTADO_MAIN = 1;
    private static final String FRAGMENTO_PRINCIPAL = "principal";
    android.support.v4.app.FragmentManager gestor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestor = getSupportFragmentManager();
        cargarFragmento(R.id.flHuecoPrincipal);
    }

    private void cargarFragmento(int id){
        FragmentTransaction transaccion = gestor.beginTransaction();
        transaccion.replace(id, FragmentoPrincipal.newInstance(), FRAGMENTO_PRINCIPAL);
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
                NuevoAlumnoActivity.startForResult(this, RESULTADO_MAIN, -1);
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
            FragmentoPrincipal fragmento = (FragmentoPrincipal) gestor.findFragmentByTag(FRAGMENTO_PRINCIPAL);
            fragmento.adaptador.notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
