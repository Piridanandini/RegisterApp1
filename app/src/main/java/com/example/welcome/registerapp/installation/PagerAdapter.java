package com.example.welcome.registerapp.installation;

/**
 * Created by welcome on 9/16/2019.
 */
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.welcome.registerapp.installation.FirstFragment;
import com.example.welcome.registerapp.installation.SecondFragment;
import com.example.welcome.registerapp.installation.ThirdFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                FirstFragment tab1 = new FirstFragment();
                return tab1;
            case 1:
                SecondFragment tab2 = new SecondFragment();
                return tab2;
            case 2:
                ThirdFragment tab3 = new ThirdFragment();
                return tab3;
            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}