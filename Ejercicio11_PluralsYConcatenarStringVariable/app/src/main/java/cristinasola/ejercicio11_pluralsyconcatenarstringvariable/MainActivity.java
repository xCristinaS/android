package cristinasola.ejercicio11_pluralsyconcatenarstringvariable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtSuspensos;
    Button btnVer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        txtSuspensos = (EditText) findViewById(R.id.txtSuspensos);
        btnVer = (Button) findViewById(R.id.btnVer);
        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), getResources().getQuantityString(R.plurals.suspensos,
                        Integer.valueOf(txtSuspensos.getText().toString()), Integer.valueOf(txtSuspensos.getText().toString())),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
