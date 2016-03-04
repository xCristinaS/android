package c.examen2t.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import c.examen2t.R;
import c.examen2t.bdd.BddContract;
import c.examen2t.modelo.Producto;

/**
 * Created by Cristina on 04/03/2016.
 */
public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ViewHolder> {

    ArrayList<Producto> productos;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView lblNombreP, lblCantidad, lblUnidad;

        public ViewHolder(View itemView) {
            super(itemView);
            lblCantidad = (TextView) itemView.findViewById(R.id.lblCantidad);
            lblNombreP = (TextView) itemView.findViewById(R.id.lblNombreP);
            lblUnidad = (TextView) itemView.findViewById(R.id.lblUnidad);
        }

        public void onBind(Producto p) {
            lblNombreP.setText(p.getNombre());
            lblCantidad.setText(String.valueOf(p.getCantidad()));
            lblUnidad.setText(p.getUnidad());
        }
    }
}
