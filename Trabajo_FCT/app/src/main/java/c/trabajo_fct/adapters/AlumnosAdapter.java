package c.trabajo_fct.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import c.trabajo_fct.R;
import c.trabajo_fct.modelos.Alumno;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Cristina on 27/02/2016.
 */
public class AlumnosAdapter extends RecyclerView.Adapter<AlumnosAdapter.ViewHolder> {

    public interface OnItemClick{
        public void onItemClick(int numCancion);
    }

    private  ArrayList<Alumno> alumnos;
    private OnItemClick listener;
    private int selectedElement = -1;

    public AlumnosAdapter(ArrayList<Alumno> alumnos){
        this.alumnos = alumnos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alumno, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (selectedElement != -1) {
            if (position == selectedElement)
                holder.itemView.setActivated(true);
            else
                holder.itemView.setActivated(false);
        }
        holder.onBind(alumnos.get(position));
    }

    @Override
    public int getItemCount() {
        return alumnos.size();
    }

    public void setOnItemClickListener(OnItemClick listener){
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView lblNombreAlumno;
        private CircleImageView imgAlumno;

        public ViewHolder(View itemView) {
            super(itemView);
            lblNombreAlumno = (TextView) itemView.findViewById(R.id.lblNombreAlumno);
            imgAlumno = (CircleImageView) itemView.findViewById(R.id.imgAlumno);
            // listener.onItemClick(getAdapterPosition(itemView)); // para hacerlo aqui.
        }

        public void onBind(final Alumno alumno){
            lblNombreAlumno.setText(alumno.getNombre());
            Picasso.with(imgAlumno.getContext()).load(alumno.getFoto()).into(imgAlumno);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //listener.onItemClick(alumnos.indexOf(c));
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
}
