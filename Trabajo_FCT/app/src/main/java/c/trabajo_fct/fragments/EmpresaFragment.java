package c.trabajo_fct.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import c.trabajo_fct.R;
import c.trabajo_fct.adapters.AlumnosAdapter;
import c.trabajo_fct.adapters.EmpresasAdapter;
import c.trabajo_fct.bdd.DAO;
import c.trabajo_fct.modelos.Alumno;
import c.trabajo_fct.modelos.Empresa;

/**
 * Created by Cristina on 27/02/2016.
 */
public class EmpresaFragment extends Fragment {

    private RecyclerView lstEmpresas;
    private EmpresasAdapter adaptador;
    private DAO gestor;

    public EmpresaFragment() {}

    // Retorna el fragmento configurado. Recibe el número de página.
    public static EmpresaFragment newInstance() {
        EmpresaFragment fragment = new EmpresaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.empresa_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        gestor = new DAO(getContext());
        //insertEmpresas();

        lstEmpresas = (RecyclerView) getView().findViewById(R.id.lstEmpresas);
        adaptador = new EmpresasAdapter(gestor.selectAllEmpresa());
        lstEmpresas.setAdapter(adaptador);
        lstEmpresas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        lstEmpresas.setItemAnimator(new DefaultItemAnimator());
        super.onActivityCreated(savedInstanceState);
    }

    private void insertEmpresas() {
        gestor.insertEmpresa(new Empresa("empresa2", "su calle", "98494840", getResources().getString(R.string.default_empresa_img)));
        gestor.insertEmpresa(new Empresa("empresa3", "su calle", "98494840", getResources().getString(R.string.default_empresa_img)));
        gestor.insertEmpresa(new Empresa("empresa4", "su calle", "98494840", getResources().getString(R.string.default_empresa_img)));
        gestor.insertEmpresa(new Empresa("empresa5", "su calle", "98494840", getResources().getString(R.string.default_empresa_img)));
        gestor.insertEmpresa(new Empresa("empresa6", "su calle", "98494840", getResources().getString(R.string.default_empresa_img)));
        gestor.insertEmpresa(new Empresa("empresa7", "su calle", "98494840", getResources().getString(R.string.default_empresa_img)));
        gestor.insertEmpresa(new Empresa("empresa18", "su calle", "98494840", getResources().getString(R.string.default_empresa_img)));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Se almacena el texto mostrado.
        //outState.putString(STATE_TEXTO, mTexto);
    }

    // Cuando se hace click sobre el fab estando en dicho fragmento.
    public void fabOnClick(View view) {
        //mTexto = getString(R.string.has_pulsado_sobre_el_fab, lblTexto.getText());
        //lblTexto.setText(mTexto);
        //Snackbar.make(view,mTexto, Snackbar.LENGTH_LONG).show();
    }
}
