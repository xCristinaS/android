package c.ejercicio42_reproduciraudioenservicio;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by Cristina on 12/02/2016.
 */
public class MyService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener{


    MediaPlayer reproductor;
    public static final String EXTRA_URL_CANCION = "url";

    @Override
    public void onCreate() {
        super.onCreate();
        reproductor = new MediaPlayer();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        if (reproductor != null){
            reproductor.stop();
            reproductor.release();
        }
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Se prepara la reproducción de la canción.
        if (reproductor != null) {
            reproductor.reset();
            reproductor.setLooping(false);
            reproductor.setAudioStreamType(AudioManager.STREAM_MUSIC);
            reproductor.setOnPreparedListener(this);
            reproductor.setOnCompletionListener(this);
            String urlCancion = intent.getStringExtra(EXTRA_URL_CANCION);
            try {
                reproductor.setDataSource(urlCancion);
                reproductor.prepareAsync();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // El servicio NO se reiniciará automáticamente y es matado por el
        // sistema.
        return START_NOT_STICKY;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        stopSelf();
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        reproductor.start();
    }
}
