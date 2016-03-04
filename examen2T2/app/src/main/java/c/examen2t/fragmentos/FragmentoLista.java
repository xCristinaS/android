package c.examen2t.fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import c.examen2t.DividerItemDecoration;
import c.examen2t.R;
import c.examen2t.activities.MainActivity;
import c.examen2t.adaptadores.ProductosAdapter;
import c.examen2t.bdd.MyContentProvider;

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
        initViews();
        super.onActivityCreated(savedInstanceState);
    }

    private void initViews(){
        lstProductos = (RecyclerView) getView().findViewById(R.id.lstProductos);
        adaptador = new ProductosAdapter();
        //adaptador.setEmptyView(getView().findViewById(R.id.lblNoHayEmpresas));
        //adaptador.setOnItemClickListener((OnAdapterItemClick) getActivity());
        //adaptador.setListenerLongClick((OnAdapterItemLongClick) getActivity());
        lstProductos.setAdapter(adaptador);
        lstProductos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        lstProductos.setItemAnimator(new DefaultItemAnimator());
        lstProductos.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));

        // Se crea el helper.
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                        return true;
                    }
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        Uri uri = Uri.parse(MyContentProvider.CONTENT_URI_PRODUCTOS  + "/" + adaptador.getProductos().get(viewHolder.getAdapterPosition()).getId());
                        getActivity().getContentResolver().delete(uri, null, null);
                    }
        });
        itemTouchHelper.attachToRecyclerView(lstProductos);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public ProductosAdapter getAdaptador() {
        return adaptador;
    }
}
