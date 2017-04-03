package com.rpham64.android.zumperproject.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.rpham64.android.zumperproject.ui.list.ListFragment;
import com.rpham64.android.zumperproject.ui.map.MapFragment;

import static com.rpham64.android.zumperproject.ui.adapters.PagerAdapter.Pages.LIST;
import static com.rpham64.android.zumperproject.ui.adapters.PagerAdapter.Pages.MAP;

/**
 * Created by Rudolf on 4/2/2017.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    public interface Pages {
        int MAP = 0;
        int LIST = 1;
    }

    public PagerAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case MAP:
                return MapFragment.newInstance();
            case LIST:
                return ListFragment.newInstance();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }
}
