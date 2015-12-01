package cristinasola.ejercicio25_dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by xCristina_S on 26/11/2015.
 */
public class MyDialogBorrar extends DialogFragment {

    public interface DialogListenerBorrar {
        public void onPositiveButtonClick(DialogFragment dialog);
        public void onNegativeButtonClick(DialogFragment dialog);
    }

    DialogListenerBorrar dialogListener;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this.getActivity());
        dialogo.setTitle("Eliminar");
        dialogo.setMessage("¿Quieres eliminar el usuario?");
        dialogo.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogListener.onPositiveButtonClick(MyDialogBorrar.this);
            }
        });
        dialogo.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogListener.onNegativeButtonClick(MyDialogBorrar.this);
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