package c.trabajo_fct.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import c.trabajo_fct.R;
import c.trabajo_fct.interfaces.OnAdapterItemClick;
import c.trabajo_fct.modelos.Empresa;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Cristina on 27/02/2016.
 */
public class EmpresasAdapter extends RecyclerView.Adapter<EmpresasAdapter.ViewHolder> {

    private ArrayList<Empresa> empresas;
    private OnAdapterItemClick listener;
    private View emptyView;
    private int selectedElement = -1;

    public EmpresasAdapter(ArrayList<Empresa> empresas){
        this.empresas = empresas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empresa, parent, false));
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
        holder.onBind(empresas.get(position));
    }

    @Override
    public int getItemCount() {
        return empresas.size();
    }

    public void setOnItemClickListener(OnAdapterItemClick listener){
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView lblNombreEmpresa;
        private CircleImageView imgLogoEmpresa;

        public ViewHolder(View itemView) {
            super(itemView);
            lblNombreEmpresa = (TextView) itemView.findViewById(R.id.lblNombreEmpresa);
            imgLogoEmpresa = (CircleImageView) itemView.findViewById(R.id.imgLogoEmpresa);
            // listener.onItemClick(getAdapterPosition(itemView)); // para hacerlo aqui.
        }

        public void onBind(final Empresa empresa){
            lblNombreEmpresa.setText(empresa.getNombre());
            Picasso.with(imgLogoEmpresa.getContext()).load(empresa.getFoto()).into(imgLogoEmpresa);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(empresa);
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
