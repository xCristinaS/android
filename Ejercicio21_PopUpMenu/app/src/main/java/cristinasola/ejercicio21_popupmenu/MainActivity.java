package cristinasola.ejercicio21_popupmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listaAlumnos;
    ArrayList<Alumno> lista;
    Adaptador adaptador;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaAlumnos = (ListView)findViewById(R.id.listaA);
        lista = new ArrayList<Alumno>();
        lista.add(new Alumno("pepe", "654785487"));
        lista.add(new Alumno("Maria", "644781245"));

        adaptador = new Adaptador(getApplicationContext(), lista);
        listaAlumnos.setAdapter(adaptador);
    }
}
