package c.examen2t.fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import c.examen2t.DividerItemDecoration;
import c.examen2t.R;
import c.examen2t.adaptadores.ProductosAdapter;

/**
 * Created by Cristina on 04/03/2016.
 */
public class FragmentoLista extends Fragment{

    private RecyclerView lstProductos;
    private ProductosAdapter adaptador;

    public static FragmentoLista newInstance() {
        return new FragmentoLista();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmento_lista, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        lstProductos = (RecyclerView) getView().findViewById(R.id.lstProductos);
        adaptador = new ProductosAdapter();
        //adaptador.setEmptyView(getView().findViewById(R.id.lblNoHayEmpresas));
        //adaptador.setOnItemClickListener((OnAdapterItemClick) getActivity());
        //adaptador.setListenerLongClick((OnAdapterItemLongClick) getActivity());
        lstProductos.setAdapter(adaptador);
        lstProductos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        lstProductos.setItemAnimator(new DefaultItemAnimator());
        lstProductos.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public ProductosAdapter getAdaptador() {
        return adaptador;
    }
}
