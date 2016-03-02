package c.trabajo_fct.interfaces;

/**
 * Created by Cristina on 01/03/2016.
 */
public interface OnAdapterItemLongClick {
    public void setAdapterAllowMultiDeletion(AdapterAllowMultiDeletion adaptador);
    public void onItemLongClick();
    public void desactivarMultiseleccion();
}
