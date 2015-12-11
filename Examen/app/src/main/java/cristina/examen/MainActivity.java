package cristina.examen;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements MyDialogFragmentEliminar.DialogListenerBorrar {

    ListView lstLibros;
    PersonalAdapterLista adaptador;
    CardView cvSinopsis;
    TextView lblSinopsis, lblTituloSinopsis;
    ImageView imgCerrar;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();
    }

    private void initViews(){
        cvSinopsis = (CardView) findViewById(R.id.cvSinopsis);
        lblSinopsis = (TextView) findViewById(R.id.lblSinopsis);
        imgCerrar = (ImageView) findViewById(R.id.imgCerrar);
        lblTituloSinopsis = (TextView) findViewById(R.id.lblTituloSinopsis);
        adaptador = new PersonalAdapterLista(this, Coleccion.getLibros());
        lstLibros = (ListView) findViewById(R.id.lstLibros);
        lstLibros.setAdapter(adaptador);

        lstLibros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cargarSinopsisLibroSeleccionado(position);
            }
        });

        lstLibros.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        lstLibros.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                ocultarSinopsis();
                mode.setTitle(lstLibros.getCheckedItemCount() + getString(R.string.de) + lstLibros.getCount());
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.menu_context_borrar, menu);
                return true;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.borrar:
                        MyDialogFragmentEliminar dialogo = new MyDialogFragmentEliminar();
                        dialogo.show(getSupportFragmentManager(), "dialogo");
                        break;
                }
                return false;
            }
        });

        imgCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ocultarSinopsis();
            }
        });
    }

    private void cargarSinopsisLibroSeleccionado(int position){
        Libro libroSeleccionado = Coleccion.getLibroAtIndex(position);
        lblSinopsis.setText(libroSeleccionado.getSinopsis());
        lblTituloSinopsis.setText(libroSeleccionado.getTitulo());
        cvSinopsis.setVisibility(View.VISIBLE);
    }

    public void onPositiveButtonClick(DialogFragment dialog) {
        if (dialog instanceof MyDialogFragmentEliminar){
            ArrayList<Libro> elems = getElementosSeleccionados(lstLibros, true);
            // Se eliminan del adaptador.
            for (Libro elemento : elems) {
                ((PersonalAdapterLista) lstLibros.getAdapter()).getLibros().remove(elemento);
            }
            // Se notifica al adaptador que ha habido cambios.
            ((PersonalAdapterLista) lstLibros.getAdapter()).notifyDataSetChanged();
        }
        Toast.makeText(MainActivity.this, "Se han eliminado los elementos seleccionados.", Toast.LENGTH_SHORT).show();
    }

    public void onNegativeButtonClick(DialogFragment dialog) {
        Toast.makeText(MainActivity.this, "Se ha cancelado la operación.", Toast.LENGTH_SHORT).show();
    }

    private ArrayList<Libro> getElementosSeleccionados(ListView lista, boolean desmarcar){
        ArrayList<Libro> datos = new ArrayList<>();
        if (datos.size() > 0)
            datos.removeAll(datos);
        // Se obtienen los elementos seleccionados de la lista.
        SparseBooleanArray selec = lista.getCheckedItemPositions();
        for (int i = 0; i < selec.size(); i++) {
            // Si está seleccionado.
            if (selec.valueAt(i)) {
                int position = selec.keyAt(i);
                // Se quita de la selección (si hay que hacerlo).
                if (desmarcar) {
                    lista.setItemChecked(position, false);
                }
                // Se añade al resultado.
                datos.add((Libro) lista.getItemAtPosition(selec.keyAt(i)));
            }
        }
        return datos;
    }

    private void ocultarSinopsis() {
        cvSinopsis.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.agregar:
                startActivity(new Intent(this, NuevoLibroActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adaptador.notifyDataSetChanged();
    }
}
