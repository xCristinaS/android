package c.examen2t.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import c.examen2t.R;
import c.examen2t.fragmentos.PreferenciasFragment;

public class PreferenciasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias);
        setTitle(getString(R.string.title_activity_pref));

        getFragmentManager().beginTransaction().replace(R.id.flHuecoPref, PreferenciasFragment.newInstance()).commit();
    }
}
