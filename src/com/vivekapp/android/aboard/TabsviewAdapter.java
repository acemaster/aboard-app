package com.vivekapp.android.aboard;

import com.vivekapp.android.aboard.ProfileActivity;
import com.vivekapp.android.aboard.Updateloc;
import com.vivekapp.android.aboard.Getaride;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsviewAdapter extends FragmentPagerAdapter {

    public TabsviewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {
      Fragment frag;
        switch (index) {
        case 0:
            frag=new ProfileActivity();
            break;
        case 1:
            frag=new Updateloc();
            break;
        case 2:
        	frag=new Getaride();
        	break;
        default:
        	frag=null;
        }
        return frag;
       
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }

}