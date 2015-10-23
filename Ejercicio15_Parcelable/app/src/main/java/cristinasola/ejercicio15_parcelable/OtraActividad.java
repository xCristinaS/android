package cristinasola.ejercicio15_parcelable;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class OtraActividad extends AppCompatActivity {

    private static final String EXTRA_DNI = "dni";
    public static final String EXTRA_NOMBRE = "nombre";
    public static final String EXTRA_EDAD = "edad";
    public static final String EXTRA_ALUMNO = "alumno";

    TextView lblDni;
    EditText txtNombre, txtEdad;
    Intent intento;
    Spinner spnSexo;

    Alumno mAlumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otra_actividad);

        initView();
        intento = getIntent();
        if (intento != null) {
            if (intento.hasExtra(EXTRA_ALUMNO))
                mAlumno = intento.getParcelableExtra(EXTRA_ALUMNO);
                lblDni.setText(mAlumno.getDni());
        }


    }
    private void initView(){
        lblDni = (TextView)findViewById(R.id.lblDni2Result);
        txtNombre = (EditText)findViewById(R.id.txtNombre2);
        txtEdad = (EditText)findViewById(R.id.txtEdad2);
        spnSexo = (Spinner) findViewById(R.id.spnSexo);

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
    // crear Intent con parcel.
    public static void start(Activity a, Parcelable alumno, int requestCode){
        Intent intento = new Intent(a, OtraActividad.class);
        intento.putExtra(EXTRA_ALUMNO, alumno);
        a.startActivityForResult(intento, requestCode);
    }

    @Override
    /*public void finish() {
        Intent intento2 = new Intent();
        intento2.putExtra(EXTRA_NOMBRE, txtNombre.getText().toString());
        intento2.putExtra(EXTRA_EDAD, txtEdad.getText().toString());
        setResult(RESULT_OK, intento2);
        super.finish();
    }
    */
    public void finish() {
        String[] sexo;
        Intent intento2 = new Intent();
        mAlumno.setNombre(txtNombre.getText().toString());
        mAlumno.setEdad(Integer.valueOf(txtEdad.getText().toString()));
        sexo = getResources().getStringArray(R.array.sexo);
        mAlumno.setSexo(sexo[((int) spnSexo.getSelectedItemId())]);
        intento2.putExtra(EXTRA_ALUMNO, mAlumno);
        setResult(RESULT_OK, intento2);
        super.finish();
    }
}
