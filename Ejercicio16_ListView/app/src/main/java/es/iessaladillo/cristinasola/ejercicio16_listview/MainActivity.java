package es.iessaladillo.cristinasola.ejercicio16_listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lstAlumnos;
    ArrayList<String> nombres = new ArrayList<String>();
    ArrayAdapter<String> adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstAlumnos = (ListView)findViewById(R.id.lstAlumnos);
        nombres.add("Pepe");
        nombres.add("Pedro");
        nombres.add("Lau");
        nombres.add("Cris");
        nombres.add("Manuel");
        nombres.add("alex");
        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, (List<String>) lstAlumnos);
        lstAlumnos.setAdapter(adaptador);

        lstAlumnos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nombre;
                nombre = (String) parent.getItemAtPosition(position);
            }
        });
    }
}
