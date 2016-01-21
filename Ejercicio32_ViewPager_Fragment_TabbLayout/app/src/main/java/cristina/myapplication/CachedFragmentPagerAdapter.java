package cristina.myapplication;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by Cristina on 21/01/2016.
 */
public abstract class CachedFragmentPagerAdapter extends FragmentPagerAdapter{
    // SparseArray de referencias débiles a los fragmentos gestionados
    // por el adaptador.
    private final ArrayList<WeakReference<Fragment>> mFragmentos = new ArrayList<>();

    public CachedFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    // Al instanciar el fragmento, se añade al SparseArray la referencia débil
    // al mismo.
    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final Fragment fragment = (Fragment) super.instantiateItem(container, position);
        mFragmentos.add(position, new WeakReference<>(fragment));
        return fragment;
    }

    // Al destruir el fragmento, se elimina su referencia débil del SparseArray.
    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        mFragmentos.remove(position);
        super.destroyItem(container, position, object);
    }

    // Retorna el fragmento correspondiente a dicha posición.
    @Nullable
    public Fragment getFragment(final int position) {
        // Se obtiene la referencia débil desde SparseArray y a partir
        // de la referencia débil se obtiene el fragmento en sí.
        final WeakReference<Fragment> wr = mFragmentos.get(position);
        if (wr != null) {
            return wr.get();
        } else {
            return null;
        }
    }
}
