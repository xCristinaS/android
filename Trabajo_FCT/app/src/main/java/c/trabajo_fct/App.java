package c.trabajo_fct;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by Cristina on 27/02/2016.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
