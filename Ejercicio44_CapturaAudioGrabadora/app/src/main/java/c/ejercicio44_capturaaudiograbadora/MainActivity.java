package c.ejercicio44_capturaaudiograbadora;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaRecorder.OnInfoListener {

    private static final int MAX_DURACION_MS = 5000;
    private MediaPlayer reproductor;
    private MediaRecorder grabadora;
    private boolean grabando = false;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnTouchListener(this);
        findViewById(R.id.btnRep).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepararReproductor();
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

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public void onInfo(MediaRecorder mr, int what, int extra) {
        if (what == MediaRecorder.MEDIA_RECORDER_INFO_MAX_DURATION_REACHED || what == MediaRecorder.MEDIA_RECORDER_INFO_MAX_FILESIZE_REACHED)
            fab.setImageResource(R.drawable.ic_mic);
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        reproductor.start();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (!grabando)
            iniciarGrabacion();
        // Si se suelta el botón, se finaliza la grabación.
        if ((event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL))
            pararGrabacion();

        return false;
    }

    private void iniciarGrabacion() {
        prepararGrabacion();
        grabadora.start();
        cambiarEstadoGrabacion(true);
    }

    private void pararGrabacion() {
        // Se detiene la grabación y se liberan los recursos de la grabadora.
        if (grabadora != null) {
            grabadora.stop();
            grabadora.release();
            grabadora = null;
        }
        // Se cambia el estado de grabación y el icono del botón.
        cambiarEstadoGrabacion(false);
        // Se prepara la reproducción.
        prepararReproductor();
    }

    private void prepararGrabacion() {
        // Se crea el objeto grabadora.
        grabadora = new MediaRecorder();
        // Se configura la grabación con fichero de salida, origen, formato,
        // tipo de codificación y duración máxima.
        String pathGrabacion = Environment.getExternalStorageDirectory().getAbsolutePath() + "/mi_audio.mp3";
        grabadora.setOutputFile(pathGrabacion);
        grabadora.setAudioSource(MediaRecorder.AudioSource.MIC);
        grabadora.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        grabadora.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        grabadora.setMaxDuration(MAX_DURACION_MS);
        grabadora.setOnInfoListener(this);
        // Se prepara la grabadora (de forma síncrona).
        try {
            grabadora.prepare();
        } catch (IOException e) {
            Log.e(getString(R.string.app_name), "Fallo en grabación");
        }
    }

    private void cambiarEstadoGrabacion(boolean b) {
        grabando = b;
        fab.setImageResource(grabando? R.drawable.ic_mic_off: R.drawable.ic_mic);
    }

    private void prepararReproductor() {
        // Si ya existía reproductor, se elimina.
        if (reproductor != null) {
            reproductor.reset();
            reproductor.release();
            reproductor = null;
        }
        // Se crea el objeto reproductor.
        reproductor = new MediaPlayer();
        try {
            // Path de la grabación.
            reproductor.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath() + "/mi_audio.mp3");
            // Stream de audio que utilizará el reproductor.
            reproductor.setAudioStreamType(AudioManager.STREAM_MUSIC);
            // Volumen
            reproductor.setVolume(1.0f, 1.0f);
            // La actividad actuará como listener cuando el reproductor ya esté
            // preparado para reproducir y cuando se haya finalizado la
            // reproducción.
            reproductor.setOnPreparedListener(this);
            reproductor.setOnCompletionListener(this);
            // Se prepara el reproductor.
            // reproductor.prepare(); // síncrona.
            reproductor.prepareAsync(); // asíncrona.
        } catch (Exception e) {
            Log.d("Reproductor", "ERROR: " + e.getMessage());
        }
    }
}
