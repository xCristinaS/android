package cristina.ejercicio30_reciclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Cristina on 14/01/2016.
 */
public class NombresAdapter extends RecyclerView.Adapter<NombresAdapter.ViewHolder>{

    private final ArrayList<Nombre> mDatos;

    // Constructor. Recibe contexto y datos.
    public NombresAdapter(ArrayList<Nombre> datos) {
        mDatos = datos;
    }

    // Retorna el número de ítems de datos.
    @Override
    public int getItemCount() {
        return mDatos.size();
    }

    // Cuando se debe crear una nueva vista que represente el ítem.
    // Recibe la vista padre que lo contendrá (para el inflado) y el tipo de vista
    // (por si hay varios tipos de ítems en el recyclerview).
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Se infla la especificación XML para obtener la vista correspondiente al ítem.
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.nombre, parent, false);
        // Se crea y retorna el contenedor de subvistas de la vista correspondiente
        // al ítem.
        return new ViewHolder(itemView);
    }

    // Cuando se deben escribir los datos en las subvistas de la vista correspondiente
    // al ítem. Recibe el contenedor y la posición del ítem en la colección de datos.
    @Override
    public void onBindViewHolder(NombresAdapter.ViewHolder holder, int position) {
        // Se obtiene el nombre correspondiente.
        Nombre nombre = mDatos.get(position);
        // Se escriben los mDatos en la vista.
        holder.lblNombre.setText(nombre.nombre);
    }

    // Contenedor de vistas para la vista correspondiente al elemento.
    static class ViewHolder extends RecyclerView.ViewHolder {

        // El contenedor de vistas para un elemento de la lista debe contener...
        private final TextView lblNombre;

        // El constructor recibe la vista correspondiente al elemento.
        public ViewHolder(View itemView) {
            // No olvidar llamar al constructor del padre.
            super(itemView);
            // Se obtienen las subvistas de la vista correspondiente al elemento.
            lblNombre = (TextView) itemView.findViewById(R.id.lblNombre);
        }
    }

    // Añade un elemento al adaptador.
    public void addItem(Nombre nombre, int position) {
        mDatos.add(position, nombre);
        notifyItemInserted(position);
    }

    // Elimina un elemento del adaptador.
    public void remove(int position) {
        mDatos.remove(position);
        notifyItemRemoved(position);
    }
}
