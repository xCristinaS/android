package cristina.examen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView lstLibros;
    PersonalAdapterLista adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews(){
        adaptador = new PersonalAdapterLista(this, Coleccion.getLibros());
        lstLibros = (ListView) findViewById(R.id.lstLibros);
        lstLibros.setAdapter(adaptador);
    }
}
