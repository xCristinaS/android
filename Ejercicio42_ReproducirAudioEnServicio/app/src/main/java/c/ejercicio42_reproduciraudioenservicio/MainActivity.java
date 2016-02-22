package c.ejercicio42_reproduciraudioenservicio;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnBoton;
    private Intent intentServicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        intentServicio = new Intent(getApplicationContext(), MyService.class);
        btnBoton = (Button) findViewById(R.id.btnBoton);
        btnBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnBoton.getText().toString().equals("Iniciar")){
                    btnBoton.setText("Parar");
                    reproducirCancion();
                } else {
                    btnBoton.setText("Iniciar");
                    pararServicio();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void reproducirCancion(){
        intentServicio.putExtra(MyService.EXTRA_URL_CANCION, "https://www.youtube.com/audiolibrary_download?vid=036500ffbf472dcc");
        startService(intentServicio);
    }

    private void pararServicio(){
        stopService(intentServicio);
    }
}
