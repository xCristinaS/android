package c.trabajo_fct_pruebadialogfragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by Cristina on 29/02/2016.
 */
public class SeleccionSimpleDialogFragment extends DialogFragment {

    // Variables.
    private SeleccionSimpleDialogListener mListener = null;
    private int mTurnoSeleccionado = 0;

    // Interfaz pública para comunicación con la actividad.
    public interface SeleccionSimpleDialogListener {
        public void onPositiveButtonClick(DialogFragment dialog, int which);
    }

    // Al crear el diálogo. Retorna el diálogo configurado.
    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder b = new AlertDialog.Builder(this.getActivity());
        b.setTitle("Turno");
        String[] turnos = {"1", "2", "3","1", "2", "3","1", "2", "3","1", "2", "3","1", "2", "3"};
        b.setSingleChoiceItems(turnos, mTurnoSeleccionado,
                new DialogInterface.OnClickListener() {
                    // Al seleccionar un elemento.
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mTurnoSeleccionado = which;
                    }
                });
        b.setIcon(R.mipmap.ic_launcher);
        // Se hace en el positivo para que salga a la derecha.
        b.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            // Al pulsar el botón positivo.
            @Override
            public void onClick(DialogInterface d, int arg1) {
                // Se cierra el diálogo.
                d.dismiss();
                // Se notifica el evento al listener indicando el índice del
                // elemento seleccionado.
                mListener.onPositiveButtonClick(
                        SeleccionSimpleDialogFragment.this, mTurnoSeleccionado);
            }
        });
        return b.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Establece la actividad como listener de los eventos del diálogo.
        try {
            mListener = (SeleccionSimpleDialogListener) activity;
        } catch (ClassCastException e) {
            // La actividad no implementa la interfaz, se lanza excepción.
            throw new ClassCastException(activity.toString()
                    + " debe implementar SeleccionSimpleDialogListener");
        }
    }

}