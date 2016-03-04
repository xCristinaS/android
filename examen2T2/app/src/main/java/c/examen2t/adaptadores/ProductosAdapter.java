package c.examen2t.adaptadores;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import c.examen2t.R;
import c.examen2t.activities.MainActivity;
import c.examen2t.bdd.BddContract;
import c.examen2t.bdd.MyContentProvider;
import c.examen2t.modelo.Producto;

/**
 * Created by Cristina on 04/03/2016.
 */
public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ViewHolder> {

    public interface ReproducirSonido{
        public void reproducir();
    }

    private ArrayList<Producto> productos;
    private static String texto_comprado;
    private SharedPreferences preferencias;
    private ReproducirSonido listener;
    private View emptyView;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        preferencias = PreferenceManager.getDefaultSharedPreferences(parent.getContext());
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (productos != null)
            holder.onBind(productos.get(position));
    }

    @Override
    public int getItemCount() {
        if (productos != null)
            return productos.size();
        else
            return 0;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
        notifyDataSetChanged();
        checkIfEmpty();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView lblNombreP, lblCantidad, lblUnidad;

        public ViewHolder(final View itemView) {
            super(itemView);
            lblCantidad = (TextView) itemView.findViewById(R.id.lblCantidad);
            lblNombreP = (TextView) itemView.findViewById(R.id.lblNombreP);
            lblUnidad = (TextView) itemView.findViewById(R.id.lblUnidad);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    conmutarComprado(productos.get(getAdapterPosition()), itemView.getContext().getContentResolver());
                    if (preferencias != null)
                        if (preferencias.getBoolean("prefSonido", false))
                            listener.reproducir();
                }
            });
        }

        public void onBind(Producto p) {
            if (preferencias != null){
                texto_comprado = preferencias.getString("prefTextoIdComprado", itemView.getContext().getString(R.string.default_asterisco));
            }

            if (p.getComprado() == MainActivity.COMPRADO)
                lblNombreP.setText(texto_comprado + p.getNombre());
            else
                lblNombreP.setText(p.getNombre());
            lblCantidad.setText(String.valueOf(p.getCantidad()));
            lblUnidad.setText(p.getUnidad());
        }
    }

    private void conmutarComprado(Producto p, ContentResolver content){
        if (p.getComprado() == MainActivity.COMPRADO)
            p.setComprado(MainActivity.NO_COMPRADO);
        else
            p.setComprado(MainActivity.COMPRADO);

        Uri uri = Uri.parse(MyContentProvider.CONTENT_URI_PRODUCTOS  + "/" + p.getId());
        ContentValues valores = new ContentValues();
        valores.put(BddContract.Producto.COMPRADO, p.getComprado());
        content.update(uri, valores, null, null);
    }

    public void setListener(ReproducirSonido listener) {
        this.listener = listener;
    }

    public void checkIfEmpty() {
        if (emptyView != null) {
            emptyView.setVisibility(getItemCount() > 0 ? View.GONE : View.VISIBLE);
        }
    }

    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
        checkIfEmpty();
    }
}
