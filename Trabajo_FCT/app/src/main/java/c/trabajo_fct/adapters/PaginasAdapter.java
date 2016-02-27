package c.trabajo_fct.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;

import c.trabajo_fct.fragments.PaginaFragment;

/**
 * Created by Cristina on 27/02/2016.
 */
public class PaginasAdapter extends  MyFragmentPagerAdapter {
    private final ArrayList<String> mTitulos;

    public PaginasAdapter(FragmentManager fm, ArrayList<String> titulos) {
        super(fm);
        this.mTitulos = titulos;
    }

    @Override
    public Fragment getItem(int position) {
        return PaginaFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return mTitulos.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitulos.get(position);
    }
}
