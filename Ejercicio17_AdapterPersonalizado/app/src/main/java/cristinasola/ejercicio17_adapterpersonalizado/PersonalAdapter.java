package cristinasola.ejercicio17_adapterpersonalizado;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by xCristina_S on 29/10/2015.
 */
public class PersonalAdapter extends ArrayAdapter<Alumno>{
    ArrayList<Alumno> alumnos;
    public PersonalAdapter(Context context, ArrayList<Alumno> alumnos){
        super(context,R.layout.layout_alumno, alumnos);
        this.alumnos = alumnos;
    }

    private static class ViewHolder{

        private TextView lblNombre;
        private TextView lblEdad;

        public ViewHolder(View itemView){
            lblNombre = (TextView) itemView.findViewById(R.id.lblNombre);
            lblEdad = (TextView) itemView.findViewById(R.id.lblEdad);
        }

        public TextView getLblEdad() {
            return lblEdad;
        }

        public TextView getLblNombre() {
            return lblNombre;
        }
    }
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_alumno, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        onBindViewHolder(holder, position);
        return convertView;
    }

    private void onBindViewHolder(ViewHolder holder, int position){
        int edad = alumnos.get(position).getEdad();
        holder.getLblNombre().setText(alumnos.get(position).getNombre());
        holder.getLblEdad().setText(String.valueOf(edad));

        if (edad < 18)
            holder.getLblEdad().setTextColor(Color.RED);
        else
            holder.getLblEdad().setTextColor(Color.BLACK);
    }
}
