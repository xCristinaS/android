package cristinasola.ejercicio03_4estaciones;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.location.GpsStatus;
import android.net.sip.SipAudioCall;
import android.net.sip.SipSession;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.EventListener;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    RadioGroup grupoRad;
    Button boton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView(){
        img = (ImageView) findViewById(R.id.imgFoto);
        grupoRad = (RadioGroup) findViewById(R.id.grupoRB);
        boton = (Button) findViewById(R.id.btnMeGusta);
        img.setImageResource(R.drawable.prim);

        boton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), grupoRad.getCheckedRadioButtonId() == R.id.primavera ?
                                getString(R.string.meGustaPrim) : grupoRad.getCheckedRadioButtonId() == R.id.invierno ? getString(R.string.meGustaInv) :
                                grupoRad.getCheckedRadioButtonId() == R.id.verano ? getString(R.string.meGustaVer) : getString(R.string.meGustaOto),
                        Toast.LENGTH_SHORT).show();
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int idRb = grupoRad.getCheckedRadioButtonId();
                if (idRb == R.id.primavera)
                    grupoRad.check(R.id.verano);
                else if (idRb == R.id.verano)
                    grupoRad.check(R.id.otonio);
                else if (idRb == R.id.otonio)
                    grupoRad.check(R.id.invierno);
                else
                    grupoRad.check(R.id.primavera);
            }
        });
        grupoRad.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.primavera)
                    img.setImageResource(R.drawable.prim);
                else if (checkedId == R.id.verano)
                    img.setImageResource(R.drawable.ver);
                else if (checkedId == R.id.invierno)
                    img.setImageResource(R.drawable.inv);
                else
                    img.setImageResource(R.drawable.oto);
            }
        });
    }
}
