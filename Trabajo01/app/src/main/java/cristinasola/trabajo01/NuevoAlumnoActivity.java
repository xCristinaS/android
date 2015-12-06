package cristinasola.trabajo01;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class NuevoAlumnoActivity extends AppCompatActivity {

    private static final String INDICE_ALUMNO = "indiceAlumno";
    TextInputLayout tilNombre, tilApellidos, tilTelefono, tilDireccion, tilEmail;
    Alumno alumno = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_alumno);

        Intent intentEntrante = getIntent();
        if (intentEntrante.hasExtra(INDICE_ALUMNO)) {
            alumno = BddAlumnos.seleccionarAlumno(intentEntrante.getIntExtra(INDICE_ALUMNO, -1));
            setTitle(getString(R.string.modificarAlumno));
        } else
            setTitle(R.string.agregarNuevoAlumno);
        initViews();
    }

    private void initViews(){
        tilNombre = (TextInputLayout) findViewById(R.id.tilNombre);
        tilApellidos = (TextInputLayout) findViewById(R.id.tilApellidos);
        tilTelefono = (TextInputLayout) findViewById(R.id.tilTelefono);
        tilDireccion = (TextInputLayout) findViewById(R.id.tilDireccion);
        tilEmail = (TextInputLayout) findViewById(R.id.tilEmail);
        if (alumno != null){
            tilNombre.getEditText().setText(alumno.getNombre());
            tilApellidos.getEditText().setText(alumno.getApellidos());
            tilTelefono.getEditText().setText(alumno.getTelefono());
            tilDireccion.getEditText().setText(alumno.getDireccion());
            tilEmail.getEditText().setText(alumno.getEmail());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_nuevo_alumno, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean r; String nombre, apellidos, telefono, direccion, email;
        switch (item.getItemId()){
            case R.id.aceptar:
                nombre = tilNombre.getEditText().getText().toString();
                apellidos = tilApellidos.getEditText().getText().toString();
                telefono = tilTelefono.getEditText().getText().toString();
                direccion = tilDireccion.getEditText().getText().toString();
                email = tilEmail.getEditText().getText().toString();
                if (alumno == null)
                    BddAlumnos.agregarAlumno(new Alumno(nombre, apellidos, telefono, direccion, email));
                else {
                    alumno.setNombre(nombre);
                    alumno.setApellidos(apellidos);
                    alumno.setDireccion(direccion);
                    alumno.setEmail(email);
                    alumno.setTelefono(telefono);
                }
                finish();
                r = true;
                break;
            default:
                r = super.onOptionsItemSelected(item);
        }
        return r;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    public static void startForResult(Activity a, int requestCode, int numAlumno){
        Intent intento = new Intent(a, NuevoAlumnoActivity.class);
        if (numAlumno != -1)
            intento.putExtra(INDICE_ALUMNO, numAlumno);
        a.startActivityForResult(intento, requestCode);
    }

    @Override
    public void finish() {
        Intent intento = new Intent();
        intento.putExtra(SecundaryActivity.EXTRA_ID_ALUMNO, BddAlumnos.indiceAlumno(alumno));
        setResult(RESULT_OK, intento);
        super.finish();
    }
}
