package cristinasola.ejercicio12_ciclovida;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String STATE_CONTADOR = "estado contador";
    public int mContador =  0;
    TextView lblTexto;
    Button btnBoton;
    EditText txtTexto;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null)
            mContador = savedInstanceState.getInt(STATE_CONTADOR);

        setContentView(R.layout.activity_main);
        initView();
    }
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_CONTADOR, mContador);
    }
    private void initView(){
        lblTexto = (TextView)findViewById(R.id.lblTexto);
        btnBoton = (Button)findViewById(R.id.btnBoton);
        txtTexto = (EditText)findViewById(R.id.txtTexto);
        lblTexto.setText(String.valueOf(mContador));

        btnBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContador++;
                lblTexto.setText(String.valueOf(mContador));
            }
        });
    }
}
