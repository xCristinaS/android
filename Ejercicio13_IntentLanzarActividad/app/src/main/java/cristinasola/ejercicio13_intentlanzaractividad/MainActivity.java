package cristinasola.ejercicio13_intentlanzaractividad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText txtTexto;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }
    private void initView(){
        txtTexto = (EditText)findViewById(R.id.txtTexto);
        findViewById(R.id.btnBoton).setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                enviar(txtTexto.getText().toString());
            }
        });
        findViewById(R.id.btnBoton2).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mostrarOtraActividad(txtTexto.getText().toString());
            }
        });
    }
    private void enviar(String s){
        Intent intento = new Intent();
        intento.setAction(Intent.ACTION_SEND);
        intento.setType("text/plain");
        intento.putExtra(android.content.Intent.EXTRA_TEXT, s);

        startActivity(intento);
    }
    private void mostrarOtraActividad(String s){
        Intent intento = new Intent(this, OtraActividad.class);
        intento.putExtra(OtraActividad.EXTRA_TEXTO, s);
        startActivity(intento);
    }
}
