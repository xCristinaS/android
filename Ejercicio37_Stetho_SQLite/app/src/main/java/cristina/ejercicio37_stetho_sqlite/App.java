package cristina.ejercicio37_stetho_sqlite;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by Cristina on 02/02/2016.
 */
public class App extends Application {
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}

