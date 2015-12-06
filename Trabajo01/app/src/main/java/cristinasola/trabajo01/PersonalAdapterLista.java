package cristinasola.trabajo01;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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
            TextView lblTelefono;
            ImageView imgFoto;

            public ViewHolder(View itemView){
                lblNombre = (TextView) itemView.findViewById(R.id.lblNombreAl);
                lblTelefono = (TextView) itemView.findViewById(R.id.lblTelefono);
                imgFoto = (ImageView) itemView.findViewById(R.id.imgFoto);
            }

            public TextView getLblNombre(){
                return lblNombre;
            }

            public TextView getLblTelefono(){
                return lblTelefono;
            }

            public ImageView getImgFoto(){
                return imgFoto;
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
        holder.getLblTelefono().setText(alumnos.get(position).getTelefono());
        Picasso.with(getContext()).load("http://lorempixel.com/image_output/cats-q-c-200-200-3.jpg").into(holder.getImgFoto());
    }

    public ArrayList<Alumno> getAlumnos(){
        return alumnos;
    }
}
