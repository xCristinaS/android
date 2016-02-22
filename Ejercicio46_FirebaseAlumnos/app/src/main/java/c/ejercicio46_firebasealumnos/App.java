package c.ejercicio46_firebasealumnos;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Cristina on 19/02/2016.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
