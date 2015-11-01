package cristinasola.ejercicio18;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import cristinasola.ejercicio18.R;

public class Otra extends AppCompatActivity {

    TextView lblNombre;
    public static final String EXTRA_NOMBRE = "nombre";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otra);

        lblNombre = (TextView) findViewById(R.id.lblNombre);
        if (getIntent() != null)
            lblNombre.setText(getIntent().getStringExtra(EXTRA_NOMBRE));
    }
}
