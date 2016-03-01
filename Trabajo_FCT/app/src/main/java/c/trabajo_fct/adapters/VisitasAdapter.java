package c.trabajo_fct.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import c.trabajo_fct.R;
import c.trabajo_fct.bdd.DAO;
import c.trabajo_fct.interfaces.AdapterAllowMultiDeletion;
import c.trabajo_fct.interfaces.OnAdapterItemClick;
import c.trabajo_fct.interfaces.OnAdapterItemLongClick;
import c.trabajo_fct.modelos.Visita;

/**
 * Created by Cristina on 28/02/2016.
 */
public class VisitasAdapter extends RecyclerView.Adapter<VisitasAdapter.ViewHolder> implements AdapterAllowMultiDeletion {

    private ArrayList<Visita> visitas;
    private OnAdapterItemClick listenerClick;
    private View emptyView;
    private OnAdapterItemLongClick listenerLongClick;
    private SparseBooleanArray mSelectedItems = new SparseBooleanArray();
    private DAO gestor;
    private boolean modoBorrarActivo = false;

    public VisitasAdapter(ArrayList<Visita> visitas) {
        this.visitas = visitas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_visita, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBind(visitas.get(position));
        holder.itemView.setActivated(mSelectedItems.get(position, false));
    }

    @Override
    public int getItemCount() {
        return visitas.size();
    }

    public void setOnItemClickListener(OnAdapterItemClick listener) {
        this.listenerClick = listener;
    }


    @Override
    public void clearAllSelections() {
        if (mSelectedItems.size() > 0) {
            mSelectedItems.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public boolean removeSelections() {
        List<Integer> seleccionados = getSelectedItemsPositions();
        Collections.sort(seleccionados, Collections.reverseOrder());
        for (int i = 0; i < seleccionados.size(); i++) {
            int pos = seleccionados.get(i);
            removeItem(pos);
        }
        return true;
    }

    public List<Integer> getSelectedItemsPositions() {
        List<Integer> items = new ArrayList<>(mSelectedItems.size());
        for (int i = 0; i < mSelectedItems.size(); i++) {
            items.add(mSelectedItems.keyAt(i));
        }
        return items;
    }

    private void removeItem(int pos) {
        gestor.deleteVisita(visitas.get(pos).getIdAlumno(), visitas.get(pos).getFecha());
        visitas.remove(pos);
        notifyItemRemoved(pos);
        checkIfEmpty();
    }

    @Override
    public void disableMultiDeletionMode() {
        modoBorrarActivo = false;
    }

    public void setListenerLongClick(OnAdapterItemLongClick listenerLongClick) {
        this.listenerLongClick = listenerLongClick;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView lblNombreAlumno, lblFechaVisita;
        private ImageView imgBarra;

        private SimpleDateFormat formatFecha = new SimpleDateFormat("EEEE dd/MM/yyyy");
        private SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");

        public ViewHolder(View itemView) {
            super(itemView);
            gestor = new DAO(itemView.getContext());
            lblNombreAlumno = (TextView) itemView.findViewById(R.id.lblNombreAlumno);
            lblFechaVisita = (TextView) itemView.findViewById(R.id.lblFechaVisita);
            imgBarra = (ImageView) itemView.findViewById(R.id.imgBarra);
        }

        public void onBind(final Visita visita) {
            Date hoy, fechaManiana, fechaSemanaSiguiente;
            lblNombreAlumno.setText(gestor.selectNombreAlumno(visita.getIdAlumno()));
            lblFechaVisita.setText(String.format("%s a las %s", formatFecha.format(visita.getFecha()), formatHora.format(visita.getFecha())));

            hoy = new Date();
            fechaManiana = new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(1));
            fechaSemanaSiguiente = new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(7));
            // menor que 0 si la primera menor q la segunda, 0 si iguales.
            if (hoy.compareTo(visita.getFecha()) > 0)
                Picasso.with(itemView.getContext()).load(R.drawable.barra_roja).into(imgBarra);
            else {
                if (fechaManiana.compareTo(visita.getFecha()) > 0)
                    Picasso.with(itemView.getContext()).load(R.drawable.barra_naranja).into(imgBarra);
                else if (fechaSemanaSiguiente.compareTo(visita.getFecha()) > 0)
                    Picasso.with(itemView.getContext()).load(R.drawable.barra_amarilla).into(imgBarra);
                else
                    Picasso.with(itemView.getContext()).load(R.drawable.barra_verde).into(imgBarra);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!modoBorrarActivo)
                        listenerClick.onItemClick(visita);
                    else {
                        if (itemView.isActivated()) {
                            itemView.setActivated(false);
                            mSelectedItems.put(visitas.indexOf(visita), false);
                        } else {
                            itemView.setActivated(true);
                            mSelectedItems.put(visitas.indexOf(visita), true);
                        }
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (listenerLongClick != null) {
                        if (!modoBorrarActivo) {
                            modoBorrarActivo = true;
                            mSelectedItems.put(visitas.indexOf(visita), true);
                            itemView.setActivated(true);
                            listenerLongClick.onItemLongClick();
                        }
                        return true;
                    } else
                        return false;
                }
            });
        }
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

    public void removeAllItems() {
        visitas.clear();
        notifyDataSetChanged();
    }

    public void reloadData(Context context) {
        DAO mGestor = new DAO(context);
        visitas.clear();
        visitas.addAll(mGestor.selectAllVisitas());
        notifyDataSetChanged();
        checkIfEmpty();
    }
}
