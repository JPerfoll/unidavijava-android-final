package br.edu.unidavi.backupdb.principal;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import br.edu.unidavi.backupdb.principal.Arquivo.ArquivoListFragment;
import br.edu.unidavi.backupdb.principal.Historico.HistoricoListFragment;

/**
 * Created by GT2A-002 on 27/04/2018.
 */

public class Pager extends FragmentStatePagerAdapter {

    int tabCount;

    public Pager (FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:  ArquivoListFragment tab1 = new ArquivoListFragment();
                return tab1;
            case 1: HistoricoListFragment tab2 = new HistoricoListFragment();
                return tab2;
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}