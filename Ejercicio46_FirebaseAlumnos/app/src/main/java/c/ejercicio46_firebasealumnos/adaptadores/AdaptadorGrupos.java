package c.ejercicio46_firebasealumnos.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import c.ejercicio46_firebasealumnos.R;
import c.ejercicio46_firebasealumnos.modelo_datos.Grupo;

/**
 * Created by Cristina on 22/02/2016.
 */
public class AdaptadorGrupos extends RecyclerView.Adapter<AdaptadorGrupos.ViewHolder>{

    private ArrayList<Grupo> data = new ArrayList<>();

    public AdaptadorGrupos(){

    }

    public AdaptadorGrupos(ArrayList<Grupo> data){
        this.data = data;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.grupo_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBind(data.get(position));
    }

    public void addItem(Grupo grupo) {
        data.add(grupo);
        notifyItemInserted(data.size() - 1);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView lblDescripcion;

        public ViewHolder(View itemView) {
            super(itemView);
            lblDescripcion = (TextView) itemView.findViewById(R.id.lblDescripcion);
        }

        public void onBind(Grupo grupo){
            lblDescripcion.setText("Grupo: " + grupo.getDescripcion());
        }
    }
}
