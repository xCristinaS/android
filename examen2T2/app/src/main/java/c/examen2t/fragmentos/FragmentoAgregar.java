package c.examen2t.fragmentos;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import c.examen2t.DividerItemDecoration;
import c.examen2t.OnFabPressed;
import c.examen2t.R;
import c.examen2t.adaptadores.ProductosAdapter;
import c.examen2t.modelo.Producto;

/**
 * Created by Cristina on 04/03/2016.
 */
public class FragmentoAgregar extends Fragment implements OnFabPressed {

    public interface ActividadAgregarListener{
        public void obtenerProducto(Producto p);
    }

    private EditText txtNombre, txtCantidad, txtUnidad;
    private ActividadAgregarListener listener;

    public static FragmentoAgregar newInstance(){
        return new FragmentoAgregar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmento_agregar, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        initViews();
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onAttach(Context context) {
        listener = (ActividadAgregarListener) context;
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        listener = null;
        super.onDetach();
    }

    public void initViews(){
        txtNombre = (EditText)getView().findViewById(R.id.txtNombre);
        txtCantidad = (EditText)getView().findViewById(R.id.txtCantidad);
        txtUnidad = (EditText)getView().findViewById(R.id.txtUnidad);
    }

    @Override
    public void onFabPressed() {
        if (camposRellenos()) {
            Producto p = new Producto();
            p.setNombre(txtNombre.getText().toString());
            p.setCantidad(Float.valueOf(txtCantidad.getText().toString()));
            p.setUnidad(txtUnidad.getText().toString());
            listener.obtenerProducto(p);
        }
    }

    private boolean camposRellenos() {
        boolean r = true;
        if (TextUtils.isEmpty(txtNombre.getText()) || TextUtils.isEmpty(txtCantidad.getText()) || TextUtils.isEmpty(txtUnidad.getText()))
            r = false;
        return r;
    }
}
