package cristina.examen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

public class NuevoLibroActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageView imgLibro;
    EditText txtTitulo, txtAutor, txtAnioPub, txtUrlPortada, txtSinopsis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_libro);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();
    }

    private void initViews(){
        imgLibro = (ImageView) findViewById(R.id.imgPortada);
        txtTitulo = (EditText) findViewById(R.id.txtTitulo);
        txtAutor = (EditText) findViewById(R.id.txtAutor);
        txtAnioPub = (EditText) findViewById(R.id.txtAnioPub);
        txtUrlPortada = (EditText) findViewById(R.id.txtUrlPortada);
        txtSinopsis = (EditText) findViewById(R.id.txtSinopsis);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nuevo_libro_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.guardar:
                agregarLibroAColeccion();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void agregarLibroAColeccion(){
        String titulo = txtTitulo.getText().toString();
        String autor = txtAutor.getText().toString();
        String sinopsis = txtSinopsis.getText().toString();
        String anioPub = txtAnioPub.getText().toString();
        String url;
        if (!TextUtils.isEmpty(txtUrlPortada.getText()))
            url = txtUrlPortada.getText().toString();
        else
            url = "";
        Coleccion.agregarLibro(new Libro(titulo, autor, anioPub, sinopsis, url));
        finish();
    }
}
