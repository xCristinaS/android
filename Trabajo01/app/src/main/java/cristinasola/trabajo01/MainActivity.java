package cristinasola.trabajo01;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    android.support.v4.app.FragmentManager gestor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BddAlumnos.agregarAlumno(new Alumno("Pepito", "1111", "Perez", "direccion1"));
        BddAlumnos.agregarAlumno(new Alumno("Juanito", "2222", "Jimenez", "direccion2"));

        gestor = getSupportFragmentManager();
        cargarFragmento(R.id.flHuecoPrincipal);
    }

    private void cargarFragmento(int id){
        FragmentTransaction transaccion = gestor.beginTransaction();
        transaccion.replace(id, FragmentoPrincipal.newInstance());
        transaccion.commit();
    }
}
