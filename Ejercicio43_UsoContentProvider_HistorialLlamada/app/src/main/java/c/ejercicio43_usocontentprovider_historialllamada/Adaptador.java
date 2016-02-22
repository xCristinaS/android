package c.ejercicio43_usocontentprovider_historialllamada;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Cristina on 17/02/2016.
 */
public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {

    private ArrayList<Llamada> llamadas;

    public Adaptador(){
    }

    public void setLlamadas(ArrayList<Llamada> llamadas){
        this.llamadas = llamadas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_llamada, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (llamadas != null)
            holder.onBind(llamadas.get(position));
    }

    @Override
    public int getItemCount() {
        if (llamadas != null)
            return llamadas.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView lblLlamada;

        public ViewHolder(View itemView) {
            super(itemView);
            lblLlamada = (TextView) itemView.findViewById(R.id.lblLlamada);
        }

        public void onBind(Llamada llamada){
            lblLlamada.setText(llamada.numero);
        }
    }
}
