package cristina.ejercicio37_stetho_sqlite;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by Cristina on 20/01/2016.
 */
public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder>{

    public interface OnItemClickListener {
        void onItemClick(Alumno alumno);
        void removeAlumno(int id);
    }

    private ArrayList<Alumno> datos;
    private OnItemClickListener listener;

    public Adaptador(ArrayList<Alumno> datos){
        this.datos = datos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.alumno_cardview, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBind(datos.get(position));
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void addItem(Alumno alumno) {
        datos.add(alumno);
        notifyItemInserted(datos.size() - 1);
    }

    public void addItems(List<Alumno> alumnos) {
        datos.addAll(alumnos);
        notifyDataSetChanged();
    }

    public int getIdLastAlumno(){
        if (!datos.isEmpty())
            return datos.get(datos.size()-1).getId();
        else
            return 0;
    }

    public void removeAllItems(){
        datos.removeAll(datos);
        notifyDataSetChanged();
    }

    public void removeItem(int posicion){
        datos.remove(posicion);
        notifyItemRemoved(posicion);
    }

    public ArrayList<Alumno> getDatos() {
        return datos;
    }

    public void setDatos(ArrayList<Alumno> datos){
        this.datos = datos;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView lblNombre, lblCurso, lblEdad;
        private ImageView imgFoto;
        private Button btnEliminar;

        public ViewHolder(View itemView) {
            super(itemView);
            lblNombre = (TextView) itemView.findViewById(R.id.lblNombre);
            lblCurso = (TextView) itemView.findViewById(R.id.lblCurso);
            lblEdad = (TextView) itemView.findViewById(R.id.lblEdad);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgFoto);
            btnEliminar = (Button) itemView.findViewById(R.id.btnEliminar);
        }

        public void onBind(final Alumno alumno){
            lblNombre.setText(alumno.getNombre());
            lblCurso.setText(alumno.getCurso());
            lblEdad.setText(alumno.getEdad().toString());
            Picasso.with(itemView.getContext()).load(alumno.getFoto()).into(imgFoto);

            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeItem(datos.indexOf(alumno));
                    listener.removeAlumno(alumno.getId());
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(alumno);
                }
            });
        }
    }
}
