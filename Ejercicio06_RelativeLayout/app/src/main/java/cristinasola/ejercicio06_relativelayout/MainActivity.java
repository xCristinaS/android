package cristinasola.ejercicio06_relativelayout;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAceptar, btnCancelar;
    EditText txtUsuario, txtClave;
    TextView lblUsuario, lblClave;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btnAceptar = (Button) findViewById(R.id.btnAceptar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        txtClave = (EditText) findViewById(R.id.txtClave);
        txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        lblUsuario = (TextView) findViewById(R.id.lblUsuario);
        lblClave = (TextView) findViewById(R.id.lblClave);

        btnAceptar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);

        txtClave.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                setColorSegunFoco(lblClave, hasFocus);
            }
        });
        txtUsuario.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                setColorSegunFoco(lblUsuario, hasFocus);
            }
        });

        txtUsuario.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            public void afterTextChanged(Editable s) {
                checkDatos();
                checkVisibility(txtUsuario, lblUsuario);
            }
        });
        txtClave.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            public void afterTextChanged(Editable s) {
                checkDatos();
                checkVisibility(txtClave, lblClave);
            }
        });
        // Comprobaciones iniciales... ¿para qué?
        setColorSegunFoco(lblUsuario, true);
        checkDatos();
        checkVisibility(txtClave, lblClave);
        checkVisibility(txtUsuario, lblUsuario);
    }

    private void setColorSegunFoco(TextView lbl, boolean hasFocus) {
        if (hasFocus) {
            //lbl.setTextColor(getResources().getColor(R.color.accent));
            lbl.setTypeface(Typeface.DEFAULT_BOLD);
        } else {
            //lbl.setTextColor(getResources().getColor(R.color.primary_text));
            lbl.setTypeface(Typeface.DEFAULT);
        }
    }

    private void checkDatos() {
        btnAceptar.setEnabled(!TextUtils.isEmpty(txtUsuario.getText().toString()) && !TextUtils.isEmpty(txtClave.getText().toString()));
    }

    private void checkVisibility(EditText txt, TextView lbl) {
        if (TextUtils.isEmpty(txt.getText().toString()))
            lbl.setVisibility(View.INVISIBLE);
        else
            lbl.setVisibility(View.VISIBLE);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAceptar:
                Toast.makeText(this, getString(R.string.conectandoUsuario) + txtUsuario.getText().toString() + getString(R.string.puntosSuspensivos), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnCancelar:
                resetViews();
                break;
        }
    }

    private void resetViews() {
        txtUsuario.setText("");
        txtClave.setText("");
    }
}