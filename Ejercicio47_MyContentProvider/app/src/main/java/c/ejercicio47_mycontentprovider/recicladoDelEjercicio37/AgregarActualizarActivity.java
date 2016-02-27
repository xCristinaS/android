package c.ejercicio47_mycontentprovider.recicladoDelEjercicio37;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import c.ejercicio47_mycontentprovider.R;

public class AgregarActualizarActivity extends AppCompatActivity {

    public static final String EXTRA_ALUMNO = "alumno";
    private static final String EXTRA_ID_AUX = "idAux";

    private Intent intento;
    private EditText txtNombre, txtEdad, txtDireccion, txtTelefono, txtRepetidor, txtCurso, txtFoto;
    private Button btnAceptar;
    private Alumno alumno;
    private int idAux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_actualizar);

        initView();

        intento = getIntent();
        if (intento != null) {
            if (intento.hasExtra(EXTRA_ALUMNO)) {
                alumno = intento.getParcelableExtra(EXTRA_ALUMNO);
                cargarAlumno();
            }
            if (intento.hasExtra(EXTRA_ID_AUX))
                idAux = intento.getIntExtra(EXTRA_ID_AUX, 0);
        }

    }

    private void initView() {
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtEdad = (EditText) findViewById(R.id.txtEdad);
        txtDireccion = (EditText) findViewById(R.id.txtDireccion);
        txtTelefono = (EditText) findViewById(R.id.txtTelefono);
        txtRepetidor = (EditText) findViewById(R.id.txtRepetidor);
        txtCurso = (EditText) findViewById(R.id.txtCurso);
        txtFoto = (EditText) findViewById(R.id.txtFoto);
        btnAceptar = (Button) findViewById(R.id.btnAceptar);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                devolverResultado();
            }
        });
    }

    private void cargarAlumno() {
        txtFoto.setText(alumno.getFoto());
        txtCurso.setText(alumno.getCurso());
        txtRepetidor.setText(String.valueOf(alumno.getRepetidor()));
        txtTelefono.setText(alumno.getTelefono());
        txtDireccion.setText(alumno.getDireccion());
        txtEdad.setText(String.valueOf(alumno.getEdad()));
        txtNombre.setText(alumno.getNombre());
    }

    public static void start(Activity a, Alumno alumno, int idAux, int requestCode) {
        Intent intento = new Intent(a, AgregarActualizarActivity.class);
        if (idAux != 0)
            intento.putExtra(EXTRA_ID_AUX, idAux);
        if (alumno != null)
            intento.putExtra(EXTRA_ALUMNO, alumno);
        a.startActivityForResult(intento, requestCode);
    }

    private void devolverResultado(){
        Intent intento2 = new Intent();
        if (alumno == null)
            alumno = new Alumno(idAux);

        alumno.setNombre(String.valueOf(txtNombre.getText()));
        alumno.setTelefono(String.valueOf(txtTelefono.getText()));
        alumno.setDireccion(String.valueOf(txtDireccion.getText()));
        alumno.setCurso(String.valueOf(txtCurso.getText()));
        alumno.setEdad(Integer.valueOf(String.valueOf(txtEdad.getText())));
        alumno.setRepetidor(txtRepetidor.toString().equals("true"));
        alumno.setCurso(String.valueOf(txtCurso.getText()));

        if (TextUtils.isEmpty(txtFoto.getText()))
            alumno.setFoto("http://lorempixel.com/400/200/sports/2/");
        else
            alumno.setFoto(txtFoto.getText().toString());

        intento2.putExtra(EXTRA_ALUMNO, alumno);
        setResult(RESULT_OK, intento2);

        finish();
    }
}
