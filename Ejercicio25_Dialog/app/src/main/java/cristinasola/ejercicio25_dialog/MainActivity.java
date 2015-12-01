package cristinasola.ejercicio25_dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MyDialog.DialogListener, MyDialogBorrar.DialogListenerBorrar, MyDialogSelecDir.SeleccionDirectaDialogListener, MyDialogSelecSimple.SelecSimpleListener {

    @Bind(R.id.boton)
    Button boton;
    @Bind(R.id.boton2)
    Button boton2;
    @Bind(R.id.boton3)
    Button boton3;
    @Bind(R.id.boton4)
    Button boton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.boton, R.id.boton2, R.id.boton3, R.id.boton4})
    public void abrirDialogo(Button btn) {
        switch (btn.getId()) {
            case R.id.boton:
                MyDialog dialogo = new MyDialog();
                dialogo.show(getSupportFragmentManager(), "dialogo");
                break;
            case R.id.boton2:
                MyDialogBorrar dialogo2 = new MyDialogBorrar();
                dialogo2.show(getSupportFragmentManager(), "dialogo2");
                break;
            case R.id.boton3:
                MyDialogSelecDir dialogo3 = new MyDialogSelecDir();
                dialogo3.show(getSupportFragmentManager(), "dialogo3");
                break;
            case R.id.boton4:
                MyDialogSelecSimple dialogo4 = new MyDialogSelecSimple();
                dialogo4.show(getSupportFragmentManager(), "dialogo4");
                break;
        }
    }

    public void onPositiveButtonClick(DialogFragment dialog) {
        //dialog.dismiss();
        if (dialog instanceof MyDialogBorrar)
            Toast.makeText(MainActivity.this, "Se ha borrado el usuario", Toast.LENGTH_SHORT).show();
    }

    public void onNegativeButtonClick(DialogFragment dialog) {
        //dialog.dismiss();
        Toast.makeText(MainActivity.this, "No se ha borrado el usuario", Toast.LENGTH_SHORT).show();
    }

    public void onItemClick(DialogFragment dialog, int which) {
        //dialog.dismiss();
        Toast.makeText(MainActivity.this, "(Selec dir) Equipo favorito: " + getResources().getStringArray(R.array.equipos)[which], Toast.LENGTH_LONG).show();
    }

    public void onPositiveButtonClick(DialogFragment dialog, int which){
        Toast.makeText(MainActivity.this, "(Selec simple) Equipo favorito: " + getResources().getStringArray(R.array.equipos)[which], Toast.LENGTH_LONG).show();
    }
}
