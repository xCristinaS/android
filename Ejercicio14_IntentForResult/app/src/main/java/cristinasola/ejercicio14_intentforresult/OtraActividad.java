package cristinasola.ejercicio14_intentforresult;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OtraActividad extends AppCompatActivity {

    private static final String EXTRA_DNI = "dni";
    public static final String EXTRA_NOMBRE = "nombre";
    public static final String EXTRA_EDAD = "edad";
    TextView lblDni;
    EditText txtNombre, txtEdad;
    Intent intento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otra_actividad);

        initView();
        intento = getIntent();
        if (intento != null) {
            if (intento.hasExtra(EXTRA_DNI))
                lblDni.setText(intento.getStringExtra(EXTRA_DNI));
        }


    }
    private void initView(){
        lblDni = (TextView)findViewById(R.id.lblDni2Result);
        txtNombre = (EditText)findViewById(R.id.txtNombre2);
        txtEdad = (EditText)findViewById(R.id.txtEdad2);
        findViewById(R.id.btnEnviar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public static void start(Activity a, String dni, int requestCode){
        Intent intento = new Intent(a, OtraActividad.class);
        intento.putExtra(EXTRA_DNI, dni);
        a.startActivityForResult(intento, requestCode);
    }

    @Override
    public void finish() {
        Intent intento2 = new Intent();
        intento2.putExtra(EXTRA_NOMBRE, txtNombre.getText().toString());
        intento2.putExtra(EXTRA_EDAD, txtEdad.getText().toString());
        setResult(RESULT_OK, intento2);
        super.finish();
    }
}
