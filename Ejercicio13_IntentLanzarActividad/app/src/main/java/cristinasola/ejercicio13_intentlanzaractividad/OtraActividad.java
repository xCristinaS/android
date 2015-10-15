package cristinasola.ejercicio13_intentlanzaractividad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OtraActividad extends AppCompatActivity {

    public static final String EXTRA_TEXTO = "texto";
    String mTexto;
    TextView lblMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otra_actividad);

        mTexto = getIntent().getStringExtra(EXTRA_TEXTO);
        initView();
    }
    private void initView(){
        lblMostrar = (TextView) findViewById(R.id.lblTextoMostrar);
        lblMostrar.setText(mTexto);
    }
}
