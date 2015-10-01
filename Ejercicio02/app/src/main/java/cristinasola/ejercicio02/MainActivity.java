package cristinasola.ejercicio02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtNombre;
    CheckBox chkCasilla;
    Button btnBoton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }
    public void initViews(){
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        chkCasilla = (CheckBox) findViewById(R.id.chkCasilla);
        btnBoton = (Button) findViewById(R.id.btnBoton);

        chkCasilla.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                  if (isChecked)
                      Toast.makeText(MainActivity.this, "Modo educado activado.", Toast.LENGTH_SHORT).show();
                  else
                      Toast.makeText(MainActivity.this, "Modo educado desactivado.", Toast.LENGTH_SHORT).show();
              }
          }
        );
    }
    public void botonClic (View v){
        if (chkCasilla.isChecked())
            Toast.makeText(this, "Hola, muy buenos días tenga sr. " + txtNombre.getText().toString(), Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Hola, ¿qué tal " + txtNombre.getText().toString() + "?", Toast.LENGTH_SHORT).show();
    }
}
