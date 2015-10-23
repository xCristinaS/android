package es.iessaladillo.cristinasola.ejercicio16_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Otra extends AppCompatActivity {

    TextView lblNombre;
    public static final String EXTRA_NOMBRE = "nombre";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otra);

        lblNombre = (TextView) findViewById(R.id.lblNombre);
    }
}
