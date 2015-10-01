package cristinasola.ejercicio01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtNombre;
    private EditText txtApellido;
    private TextView txtResult;
    private Button btnAN;
    private Button btnNA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        txtResult = (TextView) findViewById(R.id.txtResult);
        btnAN = (Button) findViewById(R.id.btnAN);
        btnNA = (Button) findViewById(R.id.btnNA);
    }

    public void btnANOnClic(View v){
        txtResult.setText(getString(R.string.sr) + " " + txtApellido.getText().toString() + ", " + txtNombre.getText().toString());
        //btnAN.setEnabled(false);
        btnAN.setVisibility(View.INVISIBLE);
        //btnNA.setEnabled(true);
        btnNA.setVisibility(View.VISIBLE);
        Toast.makeText(this, txtResult.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    public void btnNAOnClic(View v){
        txtResult.setText(getString(R.string.sr) + " " + txtNombre.getText().toString() + ", " + txtApellido.getText().toString());
        //btnNA.setEnabled(false);
        btnNA.setVisibility(View.INVISIBLE);
        //btnAN.setEnabled(true);
        btnAN.setVisibility(View.VISIBLE);
        Toast.makeText(this, txtResult.getText().toString(), Toast.LENGTH_SHORT).show();
    }
}
