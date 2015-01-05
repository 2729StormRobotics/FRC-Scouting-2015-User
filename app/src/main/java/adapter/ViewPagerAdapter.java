package adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fragments.AutoFragment;
import fragments.SubmitFragment;
import fragments.TeleFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    // Tab Titles
    private String tabtitles[] = new String[] { "Auto", "Tele", "Submit" };
    Context context;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {

            // Open FragmentTab1.java
            case 0:
                AutoFragment fragmenttab1 = new AutoFragment();
                return fragmenttab1;

            // Open FragmentTab2.java
            case 1:
                TeleFragment fragmenttab2 = new TeleFragment();
                return fragmenttab2;

            // Open FragmentTab3.java
            case 2:
                SubmitFragment fragmenttab3 = new SubmitFragment();
                return fragmenttab3;
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabtitles[position];
    }
}