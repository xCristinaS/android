package c.ejercicio47_mycontentprovider.recicladoDelEjercicio37.clases_SQLite;

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

