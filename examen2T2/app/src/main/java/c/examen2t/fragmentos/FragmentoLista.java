package c.examen2t.fragmentos;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
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
import c.examen2t.bdd.BddContract;
import c.examen2t.bdd.MyContentProvider;
import c.examen2t.modelo.Producto;

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
        adaptador.setListener((ProductosAdapter.ReproducirSonido) getActivity());
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
                        final Producto p = adaptador.getProductos().get(viewHolder.getAdapterPosition());
                        final Uri uri = Uri.parse(MyContentProvider.CONTENT_URI_PRODUCTOS  + "/" + p.getId());
                        getActivity().getContentResolver().delete(uri, null, null);

                        Snackbar.make(getView(), R.string.snackbar_message, Snackbar.LENGTH_SHORT).setAction(R.string.deshacer, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ContentValues valores = new ContentValues();
                                valores.put(BddContract.Producto.NOMBRE, p.getNombre());
                                valores.put(BddContract.Producto.CANTIDAD, p.getCantidad());
                                valores.put(BddContract.Producto.UNIDAD, p.getUnidad());
                                valores.put(BddContract.Producto.COMPRADO, p.getComprado());
                                getActivity().getContentResolver().insert(MyContentProvider.CONTENT_URI_PRODUCTOS, valores);
                            }
                        }).show();
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
