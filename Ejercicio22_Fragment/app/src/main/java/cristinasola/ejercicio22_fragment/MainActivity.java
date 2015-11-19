package cristinasola.ejercicio22_fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements UnoFragment.Callback{

    FragmentManager gestor; Button btnCambiar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestor = getSupportFragmentManager();
        btnCambiar = (Button)findViewById(R.id.btnCambiar);
        btnCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (findViewById(R.id.flHuecoSec) == null)
                    SecundariaActivity.start(MainActivity.this, "He pulsado el botón");
                else
                    cargarFragmento("Apaisado", R.id.flHuecoSec);
            }
        });
        cargarFragmento("Hola Holita!", R.id.flHueco);
    }

    private void cargarFragmento(String mensaje, int id){
        FragmentTransaction transaccion = gestor.beginTransaction();
        transaccion.replace(id, UnoFragment.newInstance(mensaje));
        transaccion.commit();
    }

    @Override
    public void meHanPulsado(String mensaje) {
        if (findViewById(R.id.flHuecoSec) == null)
            SecundariaActivity.start(MainActivity.this, mensaje);
        else
            cargarFragmento(mensaje, R.id.flHuecoSec);
    }

    // Para el menú.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean r;
        switch (item.getItemId()){
            case R.id.mnuAct:
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
