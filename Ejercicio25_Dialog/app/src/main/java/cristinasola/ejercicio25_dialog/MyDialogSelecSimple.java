package cristinasola.ejercicio25_dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by xCristina_S on 27/11/2015.
 */
public class MyDialogSelecSimple extends DialogFragment {

    private SelecSimpleListener mListener = null;
    private int mTurnoSeleccionado = 0;

    public interface SelecSimpleListener {
        public void onPositiveButtonClick(DialogFragment dialog, int which);
    }

    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder b = new AlertDialog.Builder(this.getActivity());
        b.setTitle("Equipo favorito, selección simple");
        b.setSingleChoiceItems(R.array.equipos, mTurnoSeleccionado, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mTurnoSeleccionado = which;
            }
        });
        b.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // para probar si es necesario.
                mListener.onPositiveButtonClick(MyDialogSelecSimple.this, mTurnoSeleccionado);
            }
        });
        return b.create();
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (SelecSimpleListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " debe implementar SelectSimpleListener");
        }
    }
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
