package c.trabajo_fct.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import c.trabajo_fct.DividerItemDecoration;
import c.trabajo_fct.R;
import c.trabajo_fct.adapters.EmpresasAdapter;
import c.trabajo_fct.bdd.DAO;
import c.trabajo_fct.interfaces.GestionFabDesdeFragmento;
import c.trabajo_fct.modelos.Empresa;

/**
 * Created by Cristina on 27/02/2016.
 */
public class EmpresaFragment extends Fragment implements GestionFabDesdeFragmento {

    private FragmentoPrincipal.Callback_Principal listener;
    private RecyclerView lstEmpresas;
    private EmpresasAdapter adaptador;
    private DAO gestor;

    public EmpresaFragment() {}

    public static EmpresaFragment newInstance() {
        EmpresaFragment fragment = new EmpresaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_empresa, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        gestor = new DAO(getContext());
        //insertEmpresas();

        lstEmpresas = (RecyclerView) getView().findViewById(R.id.lstEmpresas);
        adaptador = new EmpresasAdapter(gestor.selectAllEmpresa());
        adaptador.setEmptyView(getView().findViewById(R.id.lblNoHayEmpresas));
        lstEmpresas.setAdapter(adaptador);
        lstEmpresas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        lstEmpresas.setItemAnimator(new DefaultItemAnimator());
        lstEmpresas.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
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
        gestor.insertEmpresa(new Empresa("empresa18", "su calle", "98494840", getResources().getString(R.string.default_empresa_img)));
        gestor.insertEmpresa(new Empresa("empresa18", "su calle", "98494840", getResources().getString(R.string.default_empresa_img)));
        gestor.insertEmpresa(new Empresa("empresa18", "su calle", "98494840", getResources().getString(R.string.default_empresa_img)));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onFabPressed() {
        Toast.makeText(getContext(), "desde empresa", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setFabImage() {
        listener.setFabImage(R.drawable.ic_store);
    }

    public void setListener(FragmentoPrincipal.Callback_Principal listener) {
        this.listener = listener;
    }

    @Override
    public void onDetach() {
        listener = null;
        super.onDetach();
    }
}
