package cristinasola.ejercicio07_scrollview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EditText txtMensaje;
    private ImageView btnEnviar;
    private TextView lblTexto;
    private ScrollView scvTexto;
    private SimpleDateFormat formato;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        formato = new SimpleDateFormat("HH:mm:ss");
    }
    private void initView(){
        txtMensaje = (EditText)findViewById(R.id.txtMensaje);
        btnEnviar = (ImageView)findViewById(R.id.btnEnviar);
        lblTexto = (TextView)findViewById(R.id.lblTexto);
        scvTexto = (ScrollView)findViewById(R.id.scvTexto);

        txtMensaje.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE)
                    enviarMensaje(txtMensaje.getText().toString());
                return false;
            }
        });
        txtMensaje.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                checkDatos();
            }
        });
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enviarMensaje(txtMensaje.getText().toString());
            }
        });
        checkDatos();
        hacerScroll(scvTexto, View.FOCUS_DOWN);
    }
    private void checkDatos(){
        btnEnviar.setEnabled(!TextUtils.isEmpty(txtMensaje.getText().toString()));
    }
    private void enviarMensaje(String mensaje){
        if(!TextUtils.isEmpty(txtMensaje.getText().toString())){
            String hora = formato.format(new Date());
            lblTexto.append("["+hora+"] " + mensaje + "\n\n");
            txtMensaje.setText("");
            hacerScroll(scvTexto, View.FOCUS_DOWN);
        }
    }
    private void hacerScroll(final ScrollView scv, final int focus){
        scv.post(new Runnable() {
            public void run() {
                scv.fullScroll(focus);
            }
        });
    }
}
