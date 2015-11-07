package cristinasola.ejercicio20_menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txtNombre;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombre = (TextView) findViewById(R.id.txtNombre);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean r;
        switch (item.getItemId()){
            case R.id.mnuAprobar:
                Toast.makeText(MainActivity.this, txtNombre.getText() + " has aprobado!", Toast.LENGTH_SHORT).show();
                r = true;
                break;
            case R.id.mnuSuspender:
                Toast.makeText(MainActivity.this, txtNombre.getText() + " has suspendido, empolla más!", Toast.LENGTH_SHORT).show();
                r = true;
                break;
            default:
               r = super.onOptionsItemSelected(item);
        }
        return r;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.mnuAprobar).setTitle(getResources().getString(R.string.aprobar) + " a " + txtNombre.getText());
        menu.findItem(R.id.mnuSuspender).setTitle(getResources().getString(R.string.suspender) + " a " + txtNombre.getText());
        return super.onPrepareOptionsMenu(menu);
    }
}
