package cristinasola.ejercicio15_parcelable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int RC_OTRA = 1;
    private static final String ALUMNO_PARCEL = "KeyAlumno";
    Alumno mAlumno = null;

    EditText txtDni;
    TextView lblNombreResult;
    TextView lblEdadResult, lblSexo;
    Button btnObtener;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        if (savedInstanceState != null) {
            mAlumno = savedInstanceState.getParcelable(ALUMNO_PARCEL);
            alumnoToViews();
        }
    }

    private void initView(){
        txtDni = (EditText)findViewById(R.id.txtDni);
        lblNombreResult = (TextView)findViewById(R.id.lblNombreResult);
        lblEdadResult = (TextView)findViewById(R.id.lblEdadResult);
        btnObtener = (Button)findViewById(R.id.btnObtener);
        btnObtener.setEnabled(false);
        lblSexo = (TextView) findViewById(R.id.lblSexo);
        btnObtener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // OtraActividad.start(MainActivity.this, txtDni.getText().toString(), RC_OTRA);
                mAlumno = new Alumno("", 0, txtDni.getText().toString());
                OtraActividad.start(MainActivity.this, mAlumno, RC_OTRA);
            }
        });
        txtDni.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                btnObtener.setEnabled(false);
                if (!TextUtils.isEmpty(txtDni.getText().toString()))
                    btnObtener.setEnabled(true);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_OTRA && resultCode == RESULT_OK)
            if (data.hasExtra(OtraActividad.EXTRA_ALUMNO)) {
                mAlumno = data.getParcelableExtra(OtraActividad.EXTRA_ALUMNO);
                lblNombreResult.setText(mAlumno.getNombre());
                lblEdadResult.setText(String.valueOf(mAlumno.getEdad()));
                lblSexo.setText(mAlumno.getSexo());
            }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(ALUMNO_PARCEL, mAlumno);
    }

    private void alumnoToViews(){
        txtDni.setText(mAlumno.getDni());
        lblEdadResult.setText(String.valueOf(mAlumno.getEdad()));
        lblNombreResult.setText(mAlumno.getNombre());
        lblSexo.setText(mAlumno.getSexo());
    }
}
