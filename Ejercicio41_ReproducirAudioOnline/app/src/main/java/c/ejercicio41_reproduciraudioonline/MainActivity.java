package c.ejercicio41_reproduciraudioonline;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener {

    Button btnReproducir, btnPausar;
    MediaPlayer reproductor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initViews(){
        btnReproducir = (Button) findViewById(R.id.btnReproducir);
        btnPausar = (Button) findViewById(R.id.btnPausar);
        reproductor = new MediaPlayer();
        btnReproducir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepararReproductor();
            }
        });

        btnPausar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reproductor.isPlaying() && !btnPausar.getText().toString().equals("Continuar")) {
                    reproductor.pause();
                    btnPausar.setText("Continuar");
                } else if (!reproductor.isPlaying() && btnPausar.getText().toString().equals("Continuar")){
                    reproductor.start();
                    btnPausar.setText("Pausar");
                }
            }
        });
    }

    private void prepararReproductor(){
        try {
            // Se establecen sus propiedades.
            reproductor.setDataSource("https://www.youtube.com/audiolibrary_download?vid=036500ffbf472dcc");
            reproductor.setAudioStreamType(AudioManager.STREAM_MUSIC); // Música.
            // La actividad actuará como listener cuando el reproductor esté preparado.
            reproductor.setOnPreparedListener(this);
            // Se prepara el reproductor (asíncronamente)
            reproductor.prepareAsync();
        } catch (Exception e) {
            Log.d("Reproductor", "ERROR: " + e.getMessage());
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        reproductor.stop();
        reproductor.release();
    }
}
