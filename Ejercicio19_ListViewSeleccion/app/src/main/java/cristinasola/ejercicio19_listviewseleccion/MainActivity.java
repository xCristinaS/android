package cristinasola.ejercicio19_listviewseleccion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lstLista; Button btnComprobar;
    ArrayList<String> contenido = new ArrayList<String>();
    ArrayAdapter<String> adaptador;
    ImageView imagen;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contenido.add("Marron");
        contenido.add("Negro");
        contenido.add("Blanco");
        contenido.add("Verde");

        initView();
    }

    private void initView(){
        btnComprobar = (Button) findViewById(R.id.btnComprobar);
        lstLista = (ListView) findViewById(R.id.lstLista);
        adaptador = new ArrayAdapter<String>(this, R.layout.respuesta, R.id.lblRespuesta, contenido);
        lstLista.setAdapter(adaptador);
        lstLista.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        imagen = (ImageView) findViewById(R.id.imgImagen);
        Picasso.with(this).load("http://lorempixel.com/output/fashion-q-c-150-150-6.jpg").into(imagen);

        lstLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                btnComprobar.setEnabled(true);
            }
        });
        btnComprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int seleccionado = lstLista.getCheckedItemPosition();
                String cadena = (String)lstLista.getItemAtPosition(seleccionado);

                if (TextUtils.equals(cadena, "Blanco"))
                    Toast.makeText(MainActivity.this, "Has acertado!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "No has acertado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflador = getMenuInflater();
        inflador.inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
}
