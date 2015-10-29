package cristinasola.ejercicio17_adapterpersonalizado;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lista; ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView) findViewById(R.id.lstLista);
        listaAlumnos.add(new Alumno("pepito", 12, "956171819"));
        listaAlumnos.add(new Alumno("juanito", 22, "956784512"));
        listaAlumnos.add(new Alumno("lolo", 36, "956142536"));
        listaAlumnos.add(new Alumno("laura", 42, "956968574"));
        listaAlumnos.add(new Alumno("marta", 19, "956784574"));
        listaAlumnos.add(new Alumno("sonia", 10, "856741478"));
        listaAlumnos.add(new Alumno("cristina", 7, "644124578"));

        lista.setAdapter(new PersonalAdapter(this, listaAlumnos));

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intento = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+((Alumno)parent.getItemAtPosition(position)).getTelefono()));
                startActivity(intento);
            }
        });
    }
}
