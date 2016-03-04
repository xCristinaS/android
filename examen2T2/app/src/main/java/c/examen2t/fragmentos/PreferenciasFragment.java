package c.examen2t.fragmentos;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.support.v7.widget.SwitchCompat;

import c.examen2t.R;
import c.examen2t.bdd.MyContentProvider;

/**
 * Created by Cristina on 04/03/2016.
 */
public class PreferenciasFragment extends PreferenceFragment  implements SharedPreferences.OnSharedPreferenceChangeListener{

    public static PreferenciasFragment newInstance(){
        return new PreferenciasFragment();
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
        // Si es un EditTextPreference.
        if (preference instanceof EditTextPreference) {
            EditTextPreference pref = (EditTextPreference) preference;
            // Se establece como summary el valor (textual) de la preferencia.
            pref.setSummary(pref.getText());
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        actualizarSummary(findPreference(key));
        if (key.equals("prefTextoIdComprado"))
            getActivity().getContentResolver().notifyChange(MyContentProvider.CONTENT_URI_PRODUCTOS, null);
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
