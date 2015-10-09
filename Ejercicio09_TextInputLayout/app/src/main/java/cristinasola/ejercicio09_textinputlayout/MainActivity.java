package cristinasola.ejercicio09_textinputlayout;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView(){
        validarTelefono();
        validarEmail();
    }
    private void validarTelefono(){
        final TextInputLayout tilTelefono = (TextInputLayout) findViewById(R.id.tilTelefono);
        final EditText txtTelefono = (EditText) findViewById(R.id.txtTelefono);
        txtTelefono.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(txtTelefono.getText().toString())) {
                    if (!esTelefonoValido(txtTelefono.getText().toString()))
                        tilTelefono.setError("No es un número de telefono.");
                    else
                        tilTelefono.setErrorEnabled(false);
                }
            }
        });
    }
    private void validarEmail(){
        final TextInputLayout tilEmail = (TextInputLayout) findViewById(R.id.tilEmail);
        final EditText txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(txtEmail.getText().toString())){
                    if (!Patterns.EMAIL_ADDRESS.matcher(txtEmail.getText().toString()).matches())
                        tilEmail.setError("Debe tener el formato usuario@dominio.tipo");
                    else
                        tilEmail.setErrorEnabled(false);
                } else
                    tilEmail.setErrorEnabled(false);
            }
        });
    }
    private boolean esTelefonoValido(String num){
        boolean r = true;
        if (!num.startsWith("6") && !num.startsWith("7") && !num.startsWith("8") && !num.startsWith("9"))
            r = false;
        return r;
    }
}
