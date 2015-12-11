package cristina.examen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Cristina on 11/12/2015.
 */
public class PersonalAdapterLista extends ArrayAdapter<Libro> {
    ArrayList<Libro> libros;

    public PersonalAdapterLista(Context context, ArrayList<Libro> libros){
        super(context, R.layout.libro_layout, libros);
        this.libros = libros;
    }

    private static class ViewHolder {
        TextView lblTitulo;
        TextView lblAutor;
        TextView lblAnioPub;
        ImageView imgPortada;

        public ViewHolder(View itemView){
            lblTitulo = (TextView) itemView.findViewById(R.id.lblTitulo);
            lblAutor = (TextView) itemView.findViewById(R.id.lblAutor);
            lblAnioPub = (TextView) itemView.findViewById(R.id.lblAnioPub);
            imgPortada= (ImageView) itemView.findViewById(R.id.imgPortada);
        }

        public TextView getLblTitulo(){
            return lblTitulo;
        }

        public TextView getLblAutor(){
            return lblAutor;
        }

        public TextView getLblAnioPub(){
            return  lblAnioPub;
        }
        public ImageView getImgPortada(){
            return imgPortada;
        }
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.libro_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        onBindViewHolder(holder, position);
        return convertView;
    }

    private void onBindViewHolder(ViewHolder holder, int position){
        holder.getLblTitulo().setText(libros.get(position).getTitulo());
        holder.getLblAutor().setText(libros.get(position).getAutor());
        holder.getLblAnioPub().setText(libros.get(position).getAnioPublicacion());
        Picasso.with(getContext()).load(libros.get(position).getUrlPortada()).into(holder.getImgPortada());
    }

    public ArrayList<Libro> getLibros(){
        return libros;
    }
}
