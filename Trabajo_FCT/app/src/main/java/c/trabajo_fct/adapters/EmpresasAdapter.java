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
import c.trabajo_fct.modelos.Empresa;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Cristina on 27/02/2016.
 */
public class EmpresasAdapter extends RecyclerView.Adapter<EmpresasAdapter.ViewHolder> implements AdapterAllowMultiDeletion {

    private ArrayList<Empresa> empresas;
    private OnAdapterItemClick listenerClick;
    private View emptyView;
    private OnAdapterItemLongClick listenerLongClick;
    private SparseBooleanArray mSelectedItems = new SparseBooleanArray();
    private DAO gestor;
    private boolean modoBorrarActivo = false;

    public EmpresasAdapter(ArrayList<Empresa> empresas) {
        this.empresas = empresas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empresa, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBind(empresas.get(position));
        holder.itemView.setActivated(mSelectedItems.get(position, false));
    }

    @Override
    public int getItemCount() {
        return empresas.size();
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
    public void removeSelections() {
        List<Integer> seleccionados = getSelectedItemsPositions();
        Collections.sort(seleccionados, Collections.reverseOrder());
        for (int i = 0; i < seleccionados.size(); i++) {
            int pos = seleccionados.get(i);
            removeItem(pos);
        }
    }

    @Override
    public void disableMultiDeletionMode() {
        modoBorrarActivo = false;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView lblNombreEmpresa;
        private CircleImageView imgLogoEmpresa;

        public ViewHolder(View itemView) {
            super(itemView);
            lblNombreEmpresa = (TextView) itemView.findViewById(R.id.lblNombreEmpresa);
            imgLogoEmpresa = (CircleImageView) itemView.findViewById(R.id.imgLogoEmpresa);
            gestor = new DAO(itemView.getContext());
        }

        public void onBind(final Empresa empresa) {
            lblNombreEmpresa.setText(empresa.getNombre());
            Picasso.with(imgLogoEmpresa.getContext()).load(empresa.getFoto()).into(imgLogoEmpresa);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!modoBorrarActivo)
                        listenerClick.onItemClick(empresa);
                    else
                        if (itemView.isActivated()) {
                            itemView.setActivated(false);
                            mSelectedItems.put(empresas.indexOf(empresa), false);
                        } else {
                            itemView.setActivated(true);
                            mSelectedItems.put(empresas.indexOf(empresa), true);
                        }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (listenerLongClick != null) {
                        if (!modoBorrarActivo){
                            modoBorrarActivo = true;
                            mSelectedItems.put(empresas.indexOf(empresa), true);
                            itemView.setActivated(true);
                            listenerLongClick.onItemLongClick();
                        }
                        return true;
                    }  else
                        return false;
                }
            });
        }
    }

    private void removeItem(int pos) {
        gestor.deleteEmpresa(empresas.get(pos).getId());
        empresas.remove(pos);
        notifyItemRemoved(pos);
        checkIfEmpty();
    }

    // Retorna un array con las posiciones de los elementos seleccionados.
    public List<Integer> getSelectedItemsPositions() {
        List<Integer> items = new ArrayList<>(mSelectedItems.size());
        for (int i = 0; i < mSelectedItems.size(); i++) {
            items.add(mSelectedItems.keyAt(i));
        }
        return items;
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

    public void setListenerLongClick(OnAdapterItemLongClick listenerLongClick) {
        this.listenerLongClick = listenerLongClick;
    }
}
