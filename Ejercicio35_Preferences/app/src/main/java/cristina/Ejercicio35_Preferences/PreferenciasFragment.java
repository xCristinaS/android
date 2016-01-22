package cristina.Ejercicio35_Preferences;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by Cristina on 22/01/2016.
 */
public class PreferenciasFragment extends PreferenceFragment {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Se cargas las preferencias desde un recurso XML.
        addPreferencesFromResource(R.xml.preferences);
    }
}
