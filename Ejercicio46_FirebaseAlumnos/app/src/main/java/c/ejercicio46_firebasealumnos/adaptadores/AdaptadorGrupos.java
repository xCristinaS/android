package c.ejercicio46_firebasealumnos.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseRecyclerAdapter;

import c.ejercicio46_firebasealumnos.R;
import c.ejercicio46_firebasealumnos.modelo_datos.Grupo;
import c.ejercicio46_firebasealumnos.modelo_datos.GrupoAlumno;

/**
 * Created by Cristina on 22/02/2016.
 */
public class AdaptadorGrupos extends FirebaseRecyclerAdapter<GrupoAlumno, AdaptadorGrupos.ViewHolder> {


    public AdaptadorGrupos(Query ref){
        this(GrupoAlumno.class, R.layout.grupo_item, ViewHolder.class, ref);
    }

    public AdaptadorGrupos(Class<GrupoAlumno> modelClass, int modelLayout, Class<ViewHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.alumno_item, parent, false));
    }

    @Override
    protected void populateViewHolder(ViewHolder viewHolder, GrupoAlumno grupoAlumno, int i) {
        viewHolder.onBind(grupoAlumno);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView lblDescripcion;

        public ViewHolder(View itemView) {
            super(itemView);
            lblDescripcion = (TextView) itemView.findViewById(R.id.lblDescripcion);
        }

        public void onBind(GrupoAlumno grupoAlumno){
            lblDescripcion.setText("Grupo: " + grupoAlumno.getGrupo().getDescripcion());
        }
    }
}
