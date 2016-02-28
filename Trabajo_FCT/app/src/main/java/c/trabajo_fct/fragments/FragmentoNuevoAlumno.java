package c.trabajo_fct.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import c.trabajo_fct.R;

/**
 * Created by Cristina on 28/02/2016.
 */
public class FragmentoNuevoAlumno extends Fragment {

    public interface Callback_MainActivity {
        public void cargarFragmentoPrincipal();
    }

    private Callback_MainActivity listener;
    private Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmento_nuevo_alumno, container, false);
    }

    public static FragmentoNuevoAlumno newInstance(){
        return new FragmentoNuevoAlumno();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        toolbar = (Toolbar) getView().findViewById(R.id.toolbar);
        ((Toolbar) getActivity().findViewById(R.id.toolbar)).setVisibility(View.GONE);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        ImageView imgCabecera = (ImageView)getView().findViewById(R.id.imgCabecera);
        Picasso.with(getContext()).load(R.string.default_alumno_img).into(imgCabecera);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        listener = (Callback_MainActivity) context;
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        listener = null;
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        ((Toolbar) getActivity().findViewById(R.id.toolbar)).setVisibility(View.VISIBLE);
        //listener.cargarFragmentoPrincipal();
        super.onDestroy();
    }
}
