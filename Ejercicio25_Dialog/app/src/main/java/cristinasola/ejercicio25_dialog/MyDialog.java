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
public class MyDialog extends DialogFragment{

    public interface DialogListener{
        public void onPositiveButtonClick(DialogFragment dialog);
    }

    DialogListener dialogListener;

    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this.getActivity());
        dialogo.setTitle("Botón pulsado.");
        dialogo.setMessage("Pues eso, que le has hecho clic al botón");
        dialogo.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogListener.onPositiveButtonClick(MyDialog.this);
            }
        });
        return dialogo.create();
    }

    public void onAttach(Activity actividad){
        super.onAttach(actividad);
        try {
            dialogListener = (DialogListener) actividad;
        } catch(ClassCastException e){
            throw new ClassCastException(actividad.toString() + " debe implementar DialogListener");
        }
    }
    public void onDetach(){
        super.onDetach();
        dialogListener = null;
    }
}
