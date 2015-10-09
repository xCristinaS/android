package cristinasola.ejercicio10_percentrelativelayout;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.support.design.widget.Snackbar;

public class MainActivity extends AppCompatActivity {

    TextView sexto;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }
    private void initView(){
        sexto = (TextView)findViewById(R.id.sexto);
        sexto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sexto.setBackgroundColor(getResources().getColor(R.color.sexto));
                mostrarSnackbar(getString(R.string.msgSnackbar));
            }
        });
    }
    private void mostrarSnackbar(String mensaje){
        Snackbar.make(findViewById(R.id.rlRaiz),mensaje, Snackbar.LENGTH_LONG).setAction(getString(R.string.deshacer), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sexto.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.moradito)); // Los colores ahora así.
            }
        }).show();
    }
}
