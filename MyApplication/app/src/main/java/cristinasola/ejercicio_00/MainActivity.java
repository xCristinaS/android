package cristinasola.ejercicio_00;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView lblSaludo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        lblSaludo = (TextView) findViewById(R.id.lblSaludo);
    }

    public void btnUnoOnClic(View v){
        lblSaludo.setText("HelloOtraVe");
    }
}

