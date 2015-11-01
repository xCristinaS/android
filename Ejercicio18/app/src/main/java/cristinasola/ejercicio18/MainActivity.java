package cristinasola.ejercicio18;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import cristinasola.ejercicio18.R;

public class MainActivity extends AppCompatActivity {

    private static final String ESTADO_DATOS = "estadoDatos";
    ListView lstAlumnos;
    ArrayList<String> nombres;
    ArrayAdapter<String> adaptador;
    Button btnAgregar;
    EditText txtAgregarNombre;
    String borrar;
    Parcelable estadoLista;
    final String ESTADO_LISTA = "key";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAgregar = (Button) findViewById(R.id.btnAgregar);
        txtAgregarNombre = (EditText) findViewById(R.id.txtNombreAlumno);
        lstAlumnos = (ListView) findViewById(R.id.lstAlumnos);
        if (savedInstanceState == null) {
            nombres = new ArrayList<>();
            nombres.add("Pepe");
            nombres.add("Pedro");
            nombres.add("Lau");
            nombres.add("Cris");
            nombres.add("Manuel");
            nombres.add("alex");
        } else {
            estadoLista = savedInstanceState.getParcelable(ESTADO_LISTA);
            nombres = savedInstanceState.getStringArrayList(ESTADO_DATOS);
        }

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

        lstAlumnos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                borrar = (String) parent.getItemAtPosition(position);
                adaptador.remove(borrar);
                return true;
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(txtAgregarNombre.getText())) {
                    adaptador.add(txtAgregarNombre.getText().toString());
                    txtAgregarNombre.setText("");
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        estadoLista = lstAlumnos.onSaveInstanceState();
        outState.putParcelable(ESTADO_LISTA, estadoLista);
        outState.putStringArrayList(ESTADO_DATOS,nombres);
    }
}
