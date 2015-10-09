package cristinasola.ejercicio08_cardview_calculocomensales;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final float DEFAULT_CUENTA = 0.00f;
    private static final int DEFAULT_PORCENTAJE = 2;
    private static final int DEFAULT_COMENSALES = 1;
    private NumberFormat mFormateador;
    EditText txtCuenta, txtPorProp, txtPropina, txtTotal, txtComensales, txtPorCom;
    Button btnLimpiarTotal, btnRedondearTotal, btnLimpiarCom, btnRedondearCom;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFormateador = NumberFormat.getInstance(Locale.getDefault());
        initView();
    }
    private void initView(){
        txtCuenta = (EditText) findViewById(R.id.txtCuenta);
        txtPorProp = (EditText) findViewById(R.id.txtCantPorProp);
        txtPropina = (EditText) findViewById(R.id.txtPropina);
        txtTotal = (EditText) findViewById(R.id.txtTotal);
        txtComensales = (EditText) findViewById(R.id.txtComensales);
        txtPorCom = (EditText) findViewById(R.id.txtPorComensal);
        btnLimpiarCom = (Button) findViewById(R.id.btnLimpiarCom);
        btnLimpiarTotal = (Button) findViewById(R.id.btnLimpiarTotal);
        btnRedondearCom = (Button) findViewById(R.id.btnRedondearCom);
        btnRedondearTotal = (Button) findViewById(R.id.btnRedondearTotal);

        txtCuenta.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            public void afterTextChanged(Editable s) {
                calcular();
            }
        });
        txtPorProp.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            public void afterTextChanged(Editable s) {
                calcular();
            }
        });
        btnRedondearTotal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                redondearTotal();
            }
        });
        btnLimpiarTotal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                limpiarTotal();
            }
        });
        txtComensales.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            public void afterTextChanged(Editable s) {
                calcular();
            }
        });
        btnRedondearCom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                redondearCom();
            }
        });
        btnLimpiarCom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                limpiarCom();
            }
        });
        limpiarTotal();
        limpiarCom();
        calcular();
    }
    private void redondearTotal(){
       float total = Float.valueOf(txtTotal.getText().toString());
       txtTotal.setText(String.valueOf(Math.floor(total)));
    }
    private void limpiarCom(){
        txtComensales.setText(DEFAULT_COMENSALES + "");
    }
    private void limpiarTotal(){
        txtCuenta.setText(String.format(Locale.getDefault(), "%.2f",
                DEFAULT_CUENTA));
        txtPorProp.setText(DEFAULT_PORCENTAJE + "");
        txtCuenta.requestFocus();
    }
    private void calcular() {
        if (!TextUtils.isEmpty(txtCuenta.getText().toString()) &&
                !TextUtils.isEmpty(txtPorProp.getText().toString()) &&
                !TextUtils.isEmpty(txtComensales.getText().toString())) {
            float cuenta = DEFAULT_CUENTA;
            try {
                cuenta = (mFormateador.parse(txtCuenta.getText()
                        .toString())).floatValue();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            float porcentaje = DEFAULT_PORCENTAJE;
            try {
                porcentaje = (mFormateador.parse(txtPorProp.getText()
                        .toString())).floatValue();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            float comensales = DEFAULT_COMENSALES;
            try {
                comensales = (mFormateador.parse(txtComensales.getText()
                        .toString())).floatValue();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            float propina = (cuenta * porcentaje) / 100;
            float total = cuenta + propina;
            float escote = total / comensales;
            txtPropina.setText(String.format(Locale.getDefault(), "%.2f",
                    propina));
            txtTotal.setText(String.format(Locale.getDefault(), "%.2f",
                    total));
            txtPorCom.setText(String.format(Locale.getDefault(), "%.2f",
                    escote));
            btnRedondearTotal.setEnabled(true);
        } else {
            btnRedondearTotal.setEnabled(false);
        }
    }
    private void redondearCom(){
        float com = Float.valueOf(txtPorCom.getText().toString());
        txtPorCom.setText(String.valueOf(Math.floor(com)));
    }
}
