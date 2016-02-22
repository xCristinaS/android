package c.ejercicio46_firebasealumnos.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseRecyclerAdapter;

import c.ejercicio46_firebasealumnos.R;
import c.ejercicio46_firebasealumnos.modelo_datos.Alumno;

/**
 * Created by Cristina on 19/02/2016.
 */
public class AdaptadorAlumnos extends FirebaseRecyclerAdapter<Alumno, AdaptadorAlumnos.ViewHolder> {

    public interface OnAdapterAlumnoClic{
        public void onBtnNotasClick(String idAlumno);
        public void onBtnGruposClick(String idAlumno);
    }

    private OnAdapterAlumnoClic listener;

    public AdaptadorAlumnos(Query ref){
        this(Alumno.class, R.layout.alumno_item, ViewHolder.class, ref);
    }

    public AdaptadorAlumnos(Class<Alumno> modelClass, int modelLayout, Class<ViewHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.alumno_item, parent, false));
    }

    @Override
    protected void populateViewHolder(ViewHolder viewHolder, Alumno alumno, int i) {
        viewHolder.onBind(alumno, getRef(i).getKey());
    }

    public void setListener(OnAdapterAlumnoClic listener){
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView lblNombreAlumno;
        private Button btnNotas, btnGrupos;

        public ViewHolder(View itemView) {
            super(itemView);
            btnNotas = (Button) itemView.findViewById(R.id.btnNotas);
            btnGrupos = (Button) itemView.findViewById(R.id.btnGrupos);
            lblNombreAlumno = (TextView) itemView.findViewById(R.id.lblNombreAlumno);
        }

        public void onBind(final Alumno alumno, final String key){
            lblNombreAlumno.setText(alumno.getNombre());

            btnNotas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onBtnNotasClick(key);
                }
            });

            btnGrupos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onBtnGruposClick(key);
                }
            });
        }
    }
}
