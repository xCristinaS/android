package c.trabajo_fct.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import c.trabajo_fct.R;
import c.trabajo_fct.bdd.DAO;
import c.trabajo_fct.interfaces.AdapterAllowMultiDeletion;
import c.trabajo_fct.interfaces.OnAdapterItemClick;
import c.trabajo_fct.interfaces.OnAdapterItemLongClick;
import c.trabajo_fct.modelos.Alumno;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Cristina on 27/02/2016.
 */
public class AlumnosAdapter extends RecyclerView.Adapter<AlumnosAdapter.ViewHolder> implements AdapterAllowMultiDeletion {

    private ArrayList<Alumno> alumnos;
    private OnAdapterItemClick listenerClick;
    private View emptyView;
    private OnAdapterItemLongClick listenerLongClick;
    private SparseBooleanArray mSelectedItems = new SparseBooleanArray();
    private DAO gestor;
    private boolean modoBorrarActivo = false;

    public AlumnosAdapter(ArrayList<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alumno, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBind(alumnos.get(position));
        holder.itemView.setActivated(mSelectedItems.get(position, false));
    }

    @Override
    public int getItemCount() {
        return alumnos.size();
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
        boolean resp = true;
        List<Integer> seleccionados = getSelectedItemsPositions();
        Collections.sort(seleccionados, Collections.reverseOrder());
        for (int i = 0; i < seleccionados.size(); i++) {
            int pos = seleccionados.get(i);
            if (removeItem(pos))
                resp = false;
        }
        return resp;
    }

    public List<Integer> getSelectedItemsPositions() {
        List<Integer> items = new ArrayList<>(mSelectedItems.size());
        for (int i = 0; i < mSelectedItems.size(); i++) {
            items.add(mSelectedItems.keyAt(i));
        }
        return items;
    }

    private boolean removeItem(int pos) {
        boolean r = gestor.deleteVisitasDeAlumno(alumnos.get(pos).getId());
        gestor.deleteAlumno(alumnos.get(pos).getId());
        alumnos.remove(pos);
        notifyItemRemoved(pos);
        checkIfEmpty();
        return r;
    }

    @Override
    public void disableMultiDeletionMode() {
        modoBorrarActivo = false;
    }

    public void setListenerLongClick(OnAdapterItemLongClick listenerLongClick) {
        this.listenerLongClick = listenerLongClick;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView lblNombreAlumno;
        private CircleImageView imgAlumno;

        public ViewHolder(View itemView) {
            super(itemView);
            lblNombreAlumno = (TextView) itemView.findViewById(R.id.lblNombreAlumno);
            imgAlumno = (CircleImageView) itemView.findViewById(R.id.imgAlumno);
            gestor = new DAO(itemView.getContext());
        }

        public void onBind(final Alumno alumno) {
            lblNombreAlumno.setText(alumno.getNombre());
            Picasso.with(imgAlumno.getContext()).load(alumno.getFoto()).into(imgAlumno);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!modoBorrarActivo)
                        listenerClick.onItemClick(alumno);
                    else {
                        if (itemView.isActivated()) {
                            itemView.setActivated(false);
                            mSelectedItems.put(alumnos.indexOf(alumno), false);
                        } else {
                            itemView.setActivated(true);
                            mSelectedItems.put(alumnos.indexOf(alumno), true);
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
                            mSelectedItems.put(alumnos.indexOf(alumno), true);
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
        if (emptyView != null) {
            emptyView.setVisibility(getItemCount() > 0 ? View.GONE : View.VISIBLE);
        }
    }

    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
        checkIfEmpty();
    }
}
