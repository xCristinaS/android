package cristina.examen;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

}
