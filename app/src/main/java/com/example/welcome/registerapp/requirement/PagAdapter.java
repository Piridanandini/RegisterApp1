package com.example.welcome.registerapp.requirement;

/**
 * Created by welcome on 9/19/2019.
 */

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.welcome.registerapp.requirement.FirstReqFragment;
import com.example.welcome.registerapp.requirement.SecondReqFragment;
import com.example.welcome.registerapp.requirement.ThirdReqFragment;

public class PagAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                FirstReqFragment tab1 = new FirstReqFragment();
                return tab1;
            case 1:
                SecondReqFragment tab2 = new SecondReqFragment();
                return tab2;
            case 2:
                ThirdReqFragment tab3 = new ThirdReqFragment();
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
