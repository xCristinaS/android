package c.trabajo_fct.interfaces;

import c.trabajo_fct.fragments.FragmentoPrincipal;

/**
 * Created by Cristina on 28/02/2016.
 */
public interface GestionFabDesdeFragmento {
    public void onFabPressed();
    public void setFabImage();
    public void setListener(Callback_MainActivity listener);
    public Callback_MainActivity getListener();
}
