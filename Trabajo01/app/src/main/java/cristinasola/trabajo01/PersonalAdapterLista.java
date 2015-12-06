package cristinasola.trabajo01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Cristina on 06/12/2015.
 */
public class PersonalAdapterLista extends ArrayAdapter<Alumno> {

    ArrayList<Alumno> alumnos;

    public PersonalAdapterLista(Context context, ArrayList<Alumno> alumnos){
        super(context, R.layout.alumno_layout, alumnos);
        this.alumnos = alumnos;
    }

        private static class ViewHolder {
            TextView lblNombre;
            TextView lblApellidos;

            public ViewHolder(View itemView){
                lblNombre = (TextView) itemView.findViewById(R.id.lblNombreAl);
                lblApellidos = (TextView) itemView.findViewById(R.id.lblApellidos);
            }

            public TextView getLblNombre(){
                return lblNombre;
            }

            public TextView getLblApellidos(){
                return lblApellidos;
            }
        }

    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.alumno_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        onBindViewHolder(holder, position);
        return convertView;
    }

    private void onBindViewHolder(ViewHolder holder, int position){
        holder.getLblNombre().setText(alumnos.get(position).getNombre());
        holder.getLblApellidos().setText(alumnos.get(position).getApellidos());
    }

    public ArrayList<Alumno> getAlumnos(){
        return alumnos;
    }
}
