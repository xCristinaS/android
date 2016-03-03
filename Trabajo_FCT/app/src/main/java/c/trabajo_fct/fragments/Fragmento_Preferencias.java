package c.trabajo_fct.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;

import c.trabajo_fct.R;

/**
 * Created by Cristina on 03/03/2016.
 */
public class Fragmento_Preferencias extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static Fragmento_Preferencias newInstance(){
        return new Fragmento_Preferencias();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.addPreferencesFromResource(R.xml.preferencias);
        for (int i = 0; i < getPreferenceScreen().getPreferenceCount(); i++) {
            inicializarSummary(getPreferenceScreen().getPreference(i));
        }
    }

    private void inicializarSummary(Preference preference) {
        if (preference instanceof PreferenceScreen) {
            PreferenceScreen pantalla = (PreferenceScreen) preference;
            for (int i = 0; i < pantalla.getPreferenceCount(); i++) {
                inicializarSummary(pantalla.getPreference(i));
            }
        } else {
            actualizarSummary(preference);
        }
    }

    private void actualizarSummary(Preference preference) {
        if (preference instanceof ListPreference) {
            ListPreference pref = (ListPreference) preference;
            pref.setSummary(pref.getEntry());
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        actualizarSummary(findPreference(key));
    }

    @Override
    public void onPause() {
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }

    @Override
    public void onResume() {
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        super.onResume();
    }
}
