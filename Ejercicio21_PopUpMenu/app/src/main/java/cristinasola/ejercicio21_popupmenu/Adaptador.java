package cristinasola.ejercicio21_popupmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by xCristina_S on 06/11/2015.
 */
public class Adaptador extends ArrayAdapter<Alumno> {

    List<Alumno> lista;
    LayoutInflater mInflador;

    public Adaptador(Context contexto, List<Alumno> lista){
        super(contexto, R.layout.alumno_layout, lista);
        this.lista = lista;
        this.mInflador = LayoutInflater.from(contexto);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        // Si no se puede reciclar.
        if (convertView == null) {
            // Se obtiene la vista-fila inflando el layout.
            convertView = mInflador.inflate(R.layout.alumno_layout, parent, false);
            // Se crea el contenedor de vistas para la vista-fila.
            holder = new ViewHolder(convertView);
            // Se almacena el contenedor en la vista.
            convertView.setTag(holder);
        }
        // Si se puede reciclar.
        else {
            // Se obtiene el contenedor de vistas desde la vista reciclada.
            holder = (ViewHolder) convertView.getTag();
        }
        // Se escriben los datos en las vistas del contenedor de vistas.
        onBindViewHolder(holder, position);
        // Se retorna la vista que representa el elemento.
        return convertView;
    }

    private void onBindViewHolder(ViewHolder holder, int position) {
        Alumno alumno = lista.get(position);
        holder.lblNombre.setText(alumno.nombre);
        holder.lblTelefono.setText(alumno.tlf);

        holder.imgIcono.setOnClickListener(new imgClickListener(alumno));
    }

    static class ViewHolder {
        // El contenedor de vistas para un elemento de la lista debe contener...
        private final TextView lblNombre;
        private final TextView lblTelefono;
        private final ImageView imgIcono;

        // El constructor recibe la vista-fila.
        public ViewHolder(View itemView) {
            // Se obtienen las vistas de la vista-fila.
            lblNombre = (TextView) itemView
                    .findViewById(R.id.lblNombreAl);
            lblTelefono = (TextView) itemView
                    .findViewById(R.id.lblTlfAl);
            imgIcono = (ImageView) itemView.findViewById(R.id.imgMenu);
        }
    }

    public class PopUpItemClickListener implements PopupMenu.OnMenuItemClickListener {

        Alumno alumno;

        public PopUpItemClickListener(Alumno alumno){
            this.alumno = alumno;
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()){
                case R.id.envMen:
                    Toast.makeText(getContext(), "Mensaje enviado a " + alumno.nombre, Toast.LENGTH_SHORT).show();
                    break;
                case R.id.llamar:
                    Toast.makeText(getContext(), "llamando a " + alumno.tlf, Toast.LENGTH_SHORT).show();
                    break;
            }
            return  true;
        }
    }

    private class imgClickListener implements View.OnClickListener{

        Alumno alumno;

        public imgClickListener(Alumno alumno){
            this.alumno = alumno;
        }

        @Override
        public void onClick(View v) {
            PopupMenu menu = new PopupMenu(getContext(), v);
            MenuInflater infladorMenu = menu.getMenuInflater();
            infladorMenu.inflate(R.menu.menu_pop_up, menu.getMenu());

            menu.setOnMenuItemClickListener(new PopUpItemClickListener(alumno));
            menu.show();
        }
    }
}
