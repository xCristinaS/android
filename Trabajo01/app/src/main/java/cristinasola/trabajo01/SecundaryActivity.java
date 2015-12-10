package cristinasola.trabajo01;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SecundaryActivity extends AppCompatActivity implements FragmentoSecundario.Callback_FragmentoSec{

    private static final String FRAGMENTO_2_TAG = "tag";
    public static String EXTRA_ID_ALUMNO = "idAlumno";
    public static final int RESULTADO_FRAGMENT_SEC = 2;
    FragmentManager gestor;
    Intent intento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundary);

        gestor = getSupportFragmentManager();
        intento = getIntent();
        cargarFragmento(R.id.flHuecoSecundario, intento.getIntExtra(EXTRA_ID_ALUMNO, 0));
    }

    private void cargarFragmento(int id, int idAlumno){
        FragmentTransaction transaccion = gestor.beginTransaction();
        transaccion.replace(id, FragmentoSecundario.newInstance(idAlumno), FRAGMENTO_2_TAG);
        transaccion.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK)
            if (requestCode == RESULTADO_FRAGMENT_SEC && data.hasExtra(EXTRA_ID_ALUMNO)){
                FragmentoSecundario fragmento = (FragmentoSecundario) getSupportFragmentManager().findFragmentByTag(FRAGMENTO_2_TAG);
                fragmento.actualizarDatos();
            }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void editarAlumno(int idAlumno) {
        CrearModificarActivity.startForResult(this, RESULTADO_FRAGMENT_SEC, idAlumno);
    }

    public static void startForResult(Activity a, int requestCode, int posicion){
        Intent intento = new Intent(a, SecundaryActivity.class);
        intento.putExtra(SecundaryActivity.EXTRA_ID_ALUMNO, posicion);
        a.startActivityForResult(intento, requestCode);
    }

    @Override
    public void finish() {
        Intent intento = new Intent();
        setResult(RESULT_OK, intento);
        super.finish();
    }
}
