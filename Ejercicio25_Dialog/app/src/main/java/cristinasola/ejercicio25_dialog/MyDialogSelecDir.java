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
public class MyDialogSelecDir extends DialogFragment{

    private SeleccionDirectaDialogListener mListener;

    public interface SeleccionDirectaDialogListener {
        public void onItemClick(DialogFragment dialog, int which);
    }

    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder b = new AlertDialog.Builder(this.getActivity());
        b.setTitle("Equipo favorito.");
        b.setItems(R.array.equipos, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onItemClick(MyDialogSelecDir.this, which);
            }
        });
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
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
