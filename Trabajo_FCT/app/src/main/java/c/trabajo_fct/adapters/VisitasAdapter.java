package c.trabajo_fct.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    private final SparseBooleanArray mSelectedItems = new SparseBooleanArray();
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
        holder.itemView.setActivated(mSelectedItems.get(position, false));
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
        SimpleDateFormat formatFecha = new SimpleDateFormat("EEEE dd/MM/yyyy");
        SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
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
            lblFechaVisita.setText(String.format("%s a las %s", formatFecha.format(visita.getFecha()), formatHora.format(visita.getFecha())));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //listener.onItemClick(visitas.indexOf(c));
                }
            });
        }
    }
/*
    // Elimina los elementos seleccionados.
    public void removeSelectedItems() {
        // Se eliminan en orden inverso para que no haya problemas. Al
        // eliminar se cambia el
        // estado de selección del elemento.
        List<Integer> seleccionados = getSelectedItemsPositions();
        Collections.sort(seleccionados, Collections.reverseOrder());
        for (int i = 0; i < seleccionados.size(); i++) {
            int pos = seleccionados.get(i);
            toggleSelection(pos);
            removeItem(pos);
        }
        // Se comprueba si pasa a estar vacía.
        checkEmptyStateChanged();
    }
*/
    // Quita la selección de un elemento.
    public void clearSelection(int position) {
        if (mSelectedItems.get(position, false)) {
            mSelectedItems.delete(position);
        }
        notifyItemChanged(position);
    }

    // Quita la selección de todos los elementos seleccionados.
    public void clearSelections() {
        if (mSelectedItems.size() > 0) {
            mSelectedItems.clear();
            notifyDataSetChanged();
        }
    }

    // Retorna un array con las posiciones de los elementos seleccionados.
    public List<Integer> getSelectedItemsPositions() {
        List<Integer> items = new ArrayList<>(mSelectedItems.size());
        for (int i = 0; i < mSelectedItems.size(); i++) {
            items.add(mSelectedItems.keyAt(i));
        }
        return items;
    }
/*
    // Retorna el id de un elemento. Necesario para drag & drop.
    @Override
    public long getItemId(int position) {
        if (position < 0 || position >= mDatos.size()) {
            return -1;
        }
        return mDatos.get(position).getId();
    }
   */
    public void setSelectedElement(int selectedElement){
        int aux = this.selectedElement;
        this.selectedElement = selectedElement;
        notifyItemChanged(aux);
        notifyItemChanged(selectedElement);
    }

    private void checkIfEmpty() {
        if (emptyView != null)
            emptyView.setVisibility(getItemCount() > 0 ? View.GONE : View.VISIBLE);
    }

    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
        checkIfEmpty();
    }

    public void setVisitas(ArrayList<Visita> visitas) {
        this.visitas = visitas;
    }

    public void removeAllItems(){
        visitas.clear();
        notifyDataSetChanged();
    }
}
