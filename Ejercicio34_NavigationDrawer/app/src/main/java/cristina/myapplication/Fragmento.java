package cristina.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Cristina on 22/01/2016.
 */
public class Fragmento extends Fragment {
    TextView lblTexto;
    private static String texto;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_layout, container, false);
    }

    public static Fragmento newInstance(String text){
        texto = text;
        return new Fragmento();
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        lblTexto = (TextView)getView().findViewById(R.id.lblTexto);
        lblTexto.setText(texto);
        super.onActivityCreated(savedInstanceState);
    }

    public void fabPressedFromFragment() {
        Toast.makeText(getContext(), "Fab pulsado, fragmento " + texto, Toast.LENGTH_SHORT).show();
    }
}
