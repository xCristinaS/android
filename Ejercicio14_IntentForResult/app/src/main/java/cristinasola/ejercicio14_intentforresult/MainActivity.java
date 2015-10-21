package cristinasola.ejercicio14_intentforresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int RC_OTRA = 1;

    EditText txtDni;
    TextView lblNombreResult;
    TextView lblEdadResult;
    Button btnObtener;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){
        txtDni = (EditText)findViewById(R.id.txtDni);
        lblNombreResult = (TextView)findViewById(R.id.lblNombreResult);
        lblEdadResult = (TextView)findViewById(R.id.lblEdadResult);
        btnObtener = (Button)findViewById(R.id.btnObtener);
        btnObtener.setEnabled(false);
        btnObtener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OtraActividad.start(MainActivity.this, txtDni.getText().toString(), RC_OTRA);
            }
        });
        txtDni.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                btnObtener.setEnabled(false);
                if (!TextUtils.isEmpty(txtDni.getText().toString()))
                    btnObtener.setEnabled(true);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_OTRA && resultCode == RESULT_OK)
            if (data.hasExtra(OtraActividad.EXTRA_EDAD) && data.hasExtra(OtraActividad.EXTRA_NOMBRE)) {
                lblNombreResult.setText(data.getStringExtra(OtraActividad.EXTRA_NOMBRE));
                lblEdadResult.setText(data.getStringExtra(OtraActividad.EXTRA_EDAD));
            }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
