package ua.com.kistudio.birthdayapplication.ui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ua.com.kistudio.birthdayapplication.R;
import ua.com.kistudio.birthdayapplication.ui.FragmentEmpty;
import ua.com.kistudio.birthdayapplication.ui.FragmentFeedback;
import ua.com.kistudio.birthdayapplication.ui.FragmentListUser;

/**
 * Created by Вiталя on 18.03.2016.
 */
public class SectionPagerAdapter extends FragmentPagerAdapter {

    private static final int NUM_OF_TABS = 3;
    private Context mContext;

    public SectionPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new FragmentListUser();
            case 1:
                return new FragmentEmpty();
            case 2:
                return new FragmentFeedback();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_OF_TABS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return mContext.getResources().getString(R.string.users_tab_title).toString();
            case 1:
                return mContext.getResources().getString(R.string.empty_tab_title).toString();
            case 2:
                return mContext.getResources().getString(R.string.feedback_tab_title).toString();
            default:
                return super.getPageTitle(position);
        }
    }
}
