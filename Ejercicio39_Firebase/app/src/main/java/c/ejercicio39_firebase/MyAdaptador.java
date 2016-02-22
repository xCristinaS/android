package c.ejercicio39_firebase;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseRecyclerAdapter;

import java.util.List;

/**
 * Created by Cristina on 13/02/2016.
 */
public class MyAdaptador extends FirebaseRecyclerAdapter<Mensaje, MyAdaptador.MyViewHolder> {

    private static OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Mensaje mensaje, String key, int position);
    }

    public MyAdaptador(Query ref){
        this(Mensaje.class, R.layout.mensaje_layout, MyViewHolder.class, ref);
    }

    public MyAdaptador(Class modelClass, int modelLayout, Class viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Se infla la especificación XML para obtener la vista-fila.
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mensaje_layout, parent, false);
        // Se crea el contenedor de vistas para la fila.
        final MyViewHolder viewHolder = new MyViewHolder(itemView);

        // Se establecen los listener de la vista correspondiente al ítem
        // y de las subvistas.

        // Cuando se hace click sobre el elemento.
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(getItem(viewHolder.getAdapterPosition()), getRef(viewHolder.getAdapterPosition()).getKey(), viewHolder.getAdapterPosition());
            }
        });
        return viewHolder;
    }

    @Override
    protected void populateViewHolder(MyViewHolder myViewHolder, Mensaje mensaje, int i) {
        myViewHolder.lblMensaje.setText(mensaje.getMensaje());
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView lblMensaje;

        public MyViewHolder(final View itemView) {
            super(itemView);
            lblMensaje = (TextView) itemView.findViewById(R.id.lblMensaje);
        }
    }
}
