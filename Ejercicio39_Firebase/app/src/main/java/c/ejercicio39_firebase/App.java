package c.ejercicio39_firebase;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Cristina on 10/02/2016.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
