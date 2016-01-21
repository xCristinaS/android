package cristina.ejercicio33_swiperefreshlayout;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Cristina on 21/01/2016.
 */
public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {

    private ArrayList<String> fechas = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fecha_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBind(fechas.get(position));
    }

    @Override
    public int getItemCount() {
        return fechas.size();
    }

    // Añade un elemento al adaptador.
    public void addItem(String fecha, int position) {
        fechas.add(position, fecha);
        notifyItemInserted(position);
    }

    // Elimina un elemento del adaptador.
    public void remove(int position) {
        fechas.remove(position);
        notifyItemRemoved(position);
    }

    public ArrayList<String> getFechas() {
        return fechas;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView lblFecha;

        public ViewHolder(View itemView) {
            super(itemView);
            lblFecha = (TextView) itemView.findViewById(R.id.lblFecha);
        }

        public void onBind(String fecha){
            lblFecha.setText(fecha);
        }
    }
}
