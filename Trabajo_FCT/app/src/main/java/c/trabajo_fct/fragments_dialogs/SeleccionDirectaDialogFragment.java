package c.trabajo_fct.fragments_dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import c.trabajo_fct.R;

/**
 * Created by Cristina on 29/02/2016.
 */
public class SeleccionDirectaDialogFragment extends DialogFragment {

    public interface SeleccionDirectaDialogListener {
        public void onItemClick(DialogFragment dialog, int which);
    }

    private SeleccionDirectaDialogListener mListener = null;
    private static String mTitulo;
    private static String[] mOpciones;

    public static SeleccionDirectaDialogFragment newInstance(String titulo, String[] opciones){
        mTitulo = titulo;
        mOpciones = opciones;
        return new SeleccionDirectaDialogFragment();
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder b = new AlertDialog.Builder(this.getActivity());
        b.setTitle(mTitulo);
        b.setItems(mOpciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onItemClick(SeleccionDirectaDialogFragment.this, which);
            }
        });
        b.setIcon(R.drawable.ic_person);
        return b.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (SeleccionDirectaDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " debe implementar SeleccionDirectaDialogListener");
        }
    }
}
