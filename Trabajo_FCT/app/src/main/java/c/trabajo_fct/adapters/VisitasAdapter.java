package c.trabajo_fct.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import c.trabajo_fct.R;
import c.trabajo_fct.bdd.DAO;
import c.trabajo_fct.modelos.Empresa;
import c.trabajo_fct.modelos.Visita;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Cristina on 28/02/2016.
 */
public class VisitasAdapter extends RecyclerView.Adapter<VisitasAdapter.ViewHolder> {

    public interface OnItemClick{
        public void onItemClick(int numCancion);
    }

    private ArrayList<Visita> visitas;
    private OnItemClick listener;
    private View emptyView;
    private int selectedElement = -1;

    public VisitasAdapter(ArrayList<Visita> visitas){
        this.visitas = visitas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_visita, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        /*if (selectedElement != -1) {
            if (position == selectedElement)
                holder.itemView.setActivated(true);
            else
                holder.itemView.setActivated(false);
        }
        */
        holder.onBind(visitas.get(position));
    }

    @Override
    public int getItemCount() {
        return visitas.size();
    }

    public void setOnItemClickListener(OnItemClick listener){
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView lblNombreAlumno, lblFechaVisita;
        private SimpleDateFormat formato = new SimpleDateFormat("EEEE hh/MM/yyyy HH:mm:ss");
        private DAO gestor;

        public ViewHolder(View itemView) {
            super(itemView);
            gestor = new DAO(itemView.getContext());
            lblNombreAlumno = (TextView) itemView.findViewById(R.id.lblNombreAlumno);
            lblFechaVisita = (TextView) itemView.findViewById(R.id.lblFechaVisita);
            // listener.onItemClick(getAdapterPosition(itemView)); // para hacerlo aqui.
        }

        public void onBind(final Visita visita){
            lblNombreAlumno.setText(gestor.selectNombreAlumno(visita.getIdAlumno()));
            lblFechaVisita.setText(formato.format(visita.getFecha()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //listener.onItemClick(visitas.indexOf(c));
                }
            });
        }
    }

    public void setSelectedElement(int selectedElement){
        int aux = this.selectedElement;
        this.selectedElement = selectedElement;
        notifyItemChanged(aux);
        notifyItemChanged(selectedElement);
    }

    private void checkIfEmpty() {
        if (emptyView != null) {
            emptyView.setVisibility(getItemCount() > 0 ? View.GONE : View.VISIBLE);
        }
    }

    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
        checkIfEmpty();
    }
}
