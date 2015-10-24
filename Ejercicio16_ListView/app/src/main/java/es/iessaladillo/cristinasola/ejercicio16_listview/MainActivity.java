package es.iessaladillo.cristinasola.ejercicio16_listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lstAlumnos;
    ArrayList<String> nombres = new ArrayList<String>();
    ArrayAdapter<String> adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstAlumnos = (ListView) findViewById(R.id.lstAlumnos);
        nombres.add("Pepe");
        nombres.add("Pedro");
        nombres.add("Lau");
        nombres.add("Cris");
        nombres.add("Manuel");
        nombres.add("alex");
        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombres);
        lstAlumnos.setAdapter(adaptador);

        lstAlumnos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View view, int position, long id) {
                Intent intento = new Intent(MainActivity.this, Otra.class);
                intento.putExtra(Otra.EXTRA_NOMBRE, lista.getItemAtPosition(position).toString());
                startActivity(intento);
            }
        });
    }
}
