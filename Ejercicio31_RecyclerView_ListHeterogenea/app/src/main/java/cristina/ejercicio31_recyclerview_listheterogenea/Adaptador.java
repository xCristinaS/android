package cristina.ejercicio31_recyclerview_listheterogenea;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Cristina on 15/01/2016.
 */
public class Adaptador extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // Interfaz que debe implementar el listener para cuando se haga click sobre un elemento.
    public interface OnItemClickListener {
        void onItemClick(View view, TipoAlumno alumno, int position);
    }

    private final ArrayList<TipoGenerico> mDatos;
    private OnItemClickListener onItemClickListener;

    public Adaptador(ArrayList<TipoGenerico> datos) {
        mDatos = datos;
    }

    @Override
    public int getItemCount() {
        return mDatos.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mDatos.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TipoGenerico.TYPE_ALUMNO)
            return onCreateAlumnoViewHolder(parent);
        else
            return onCreateCursoViewHolder(parent);

    }

    private RecyclerView.ViewHolder onCreateAlumnoViewHolder(ViewGroup parent) {
        // Se infla el layout.
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.alumno_layout, parent, false);
        // Se crea el contenedor de vistas para la fila.
        final RecyclerView.ViewHolder viewHolder = new AlumnoViewHolder(itemView);
        // Cuando se hace click sobre el elemento.
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    // Se informa al listener.
                    onItemClickListener.onItemClick(v, (TipoAlumno) mDatos.get(viewHolder.getAdapterPosition()), viewHolder.getAdapterPosition());
                }
            }
        });
        // Se retorna el contenedor.
        return viewHolder;
    }

    // Cuando se debe crear una nueva vista para un elemento de tipo Grupo.
    private RecyclerView.ViewHolder onCreateCursoViewHolder(ViewGroup parent) {
        // Se infla el layout.
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.curso_layout, parent, false);
        // Se crea el contenedor de vistas para la fila.
        final RecyclerView.ViewHolder viewHolder = new GrupoViewHolder(itemView);
        // Cuando se hace click sobre el elemento.
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        // Se retorna el contenedor.
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mDatos.get(position).getType() == TipoGenerico.TYPE_ALUMNO) {
            ((AlumnoViewHolder) holder).onBind((TipoAlumno) mDatos.get(position));
        } else {
            ((GrupoViewHolder) holder).onBind((TipoCurso) mDatos.get(position));
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public void removeItem(int position) {
        mDatos.remove(position);
        notifyItemRemoved(position);
    }

    public void addItem(TipoAlumno alumno, int position) {
        // Se añade el elemento.
        mDatos.add(position, alumno);
        // Se notifica que se ha insertado un elemento en la última posición.
        notifyItemInserted(position);
    }

    // Contenedor de vistas para la vista-fila.
    static class AlumnoViewHolder extends RecyclerView.ViewHolder {

        // El contenedor de vistas para un elemento de la lista debe contener...
        private final TextView lblNombre;

        // El constructor recibe la vista-fila.
        public AlumnoViewHolder(View itemView) {
            super(itemView);
            // Se obtienen las vistas de la vista-fila.
            lblNombre = (TextView) itemView.findViewById(R.id.lblNombre);
        }

        public void onBind(TipoAlumno alumno) {
            // Se escriben los datos en la vista.
            lblNombre.setText(alumno.getNombreAl());
        }
    }

    // Contenedor de vistas para la vista-fila.
    static class GrupoViewHolder extends RecyclerView.ViewHolder {

        // El contenedor de vistas para un elemento de la lista debe contener...
        private final TextView lblNombreCurso;

        // El constructor recibe la vista-fila.
        public GrupoViewHolder(View itemView) {
            super(itemView);
            // Se obtienen las vistas de la vista-fila.
            lblNombreCurso = (TextView) itemView.findViewById(R.id.lblNombreCurso);
        }

        public void onBind(TipoCurso curso) {
            // Se escriben los datos en la vista.
            lblNombreCurso.setText(curso.getNombreCurso());
        }
    }
}
