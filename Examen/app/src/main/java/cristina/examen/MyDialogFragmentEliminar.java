package cristina.examen;

import android.app.Activity;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

/**
 * Created by Cristina on 11/12/2015.
 */
public class MyDialogFragmentEliminar extends DialogFragment {

    public interface DialogListenerBorrar {
        public void onPositiveButtonClick(DialogFragment dialog);
        public void onNegativeButtonClick(DialogFragment dialog);
    }

    DialogListenerBorrar dialogListener;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this.getActivity());
        dialogo.setTitle("Eliminación");
        dialogo.setMessage("¿Está seguro de que quiere eliminar los elementos seleccionados?");
        dialogo.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogListener.onPositiveButtonClick(MyDialogFragmentEliminar.this);
            }
        });
        dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogListener.onNegativeButtonClick(MyDialogFragmentEliminar.this);
            }
        });
        return dialogo.create();
    }

    public void onAttach(Activity actividad) {
        super.onAttach(actividad);
        try {
            dialogListener = (DialogListenerBorrar) actividad;
        } catch (ClassCastException e) {
            throw new ClassCastException(actividad.toString() + " debe implementar DialogListenerBorrar");
        }
    }

    public void onDetach() {
        super.onDetach();
        dialogListener = null;
    }
}
