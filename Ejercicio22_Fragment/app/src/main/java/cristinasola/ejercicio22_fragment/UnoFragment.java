package cristinasola.ejercicio22_fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by xCristina_S on 12/11/2015.
 */
public class UnoFragment extends Fragment{
    public interface Callback {
        public void meHanPulsado(String mensaje);
    }
    public static final String CLAVE_MSG = "a";
    public Callback listener; TextView txtMensaje, lblMensaje; Button btn2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_uno, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String mensaje = getArguments().getString(CLAVE_MSG);

        ((TextView)getView().findViewById(R.id.lblMensaje)).setText(mensaje);

        btn2 = (Button)getView().findViewById(R.id.btnBoton2);
        txtMensaje = (TextView)getView().findViewById(R.id.txtMensaje);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.meHanPulsado(txtMensaje.getText().toString());
            }
        });
    }

    public static UnoFragment newInstance(String mensaje){
        UnoFragment fragmento = new UnoFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CLAVE_MSG, mensaje);
        fragmento.setArguments(bundle);
        return fragmento;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (Callback) activity;
        } catch (ClassCastException e){
            throw new ClassCastException("La actividad debe implementar la interfaz Callback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
