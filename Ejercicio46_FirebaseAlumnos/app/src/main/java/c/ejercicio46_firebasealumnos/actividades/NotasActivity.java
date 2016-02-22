package c.ejercicio46_firebasealumnos.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.w3c.dom.Text;

import c.ejercicio46_firebasealumnos.R;
import c.ejercicio46_firebasealumnos.modelo_datos.Nota;

public class NotasActivity extends AppCompatActivity {

    private TextView lblIngles, lblMates, lblLengua, lblSociales;
    public static final String EXTRA_KEY_ALUMNO = "key";
    private Intent intentRecibido;
    private Firebase myFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();
        intentRecibido = getIntent();
        if (intentRecibido != null && intentRecibido.hasExtra(EXTRA_KEY_ALUMNO))
            obtenerNotasAlumno(intentRecibido.getStringExtra(EXTRA_KEY_ALUMNO));
    }

    private void initViews() {
        lblIngles = (TextView) findViewById(R.id.lblIngles);
        lblLengua = (TextView) findViewById(R.id.lblLengua);
        lblMates = (TextView) findViewById(R.id.lblMates);
        lblSociales = (TextView) findViewById(R.id.lblSociales);
    }

    private void obtenerNotasAlumno(String keyAlumno){
        myFirebase = new Firebase(MainActivity.DIR_MYBDD + MainActivity.ARBOL_NOTAS + keyAlumno);
        myFirebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lblSociales.setText("Sociales: " + dataSnapshot.getValue(Nota.class).getSociales());
                lblMates.setText("Mates: " + dataSnapshot.getValue(Nota.class).getMates());
                lblIngles.setText("Inglés: " + dataSnapshot.getValue(Nota.class).getIngles());
                lblLengua.setText("Lengua: " + dataSnapshot.getValue(Nota.class).getLengua());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
