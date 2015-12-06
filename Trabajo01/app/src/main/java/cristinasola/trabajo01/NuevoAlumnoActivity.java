package cristinasola.trabajo01;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

public class NuevoAlumnoActivity extends AppCompatActivity {

    TextInputLayout tilNombre, tilApellidos, tilTelefono, tilDireccion, tilEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_alumno);

        initViews();
    }

    private void initViews(){
        tilNombre = (TextInputLayout) findViewById(R.id.tilNombre);
        tilApellidos = (TextInputLayout) findViewById(R.id.tilApellidos);
        tilTelefono = (TextInputLayout) findViewById(R.id.tilTelefono);
        tilDireccion = (TextInputLayout) findViewById(R.id.tilDireccion);
        tilEmail = (TextInputLayout) findViewById(R.id.tilEmail);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_nuevo_alumno, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean r; String nombre = "", apellidos = "", telefono = "", direccion = "", email = "";
        switch (item.getItemId()){
            case R.id.aceptar:
                if (!TextUtils.isEmpty(tilNombre.getEditText().getText()))
                    nombre = tilNombre.getEditText().getText().toString();
                if (!TextUtils.isEmpty(tilApellidos.getEditText().getText()))
                    apellidos = tilApellidos.getEditText().getText().toString();
                if (!TextUtils.isEmpty(tilTelefono.getEditText().getText()))
                    telefono = tilTelefono.getEditText().getText().toString();
                if (!TextUtils.isEmpty(tilDireccion.getEditText().getText()))
                    direccion = tilDireccion.getEditText().getText().toString();
                if (!TextUtils.isEmpty(tilEmail.getEditText().getText()))
                    email = tilEmail.getEditText().getText().toString();
                BddAlumnos.agregarAlumno(new Alumno(nombre, apellidos, telefono, direccion, email));
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

    public static void startForResult(Activity a, int requestCode){
        Intent intento = new Intent(a, NuevoAlumnoActivity.class);
        a.startActivityForResult(intento, requestCode);
    }

    @Override
    public void finish() {
        Intent intento = new Intent();
        setResult(RESULT_OK, intento);
        super.finish();
    }
}
