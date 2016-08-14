package za.co.smileyjoedev.tincar.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import za.co.smileyjoedev.tincar.R;
import za.co.smileyjoedev.tincar.fragment.CarListFragment;
import za.co.smileyjoedev.tincar.object.Car;

/**
 * Created by cody on 2016/08/14.
 */
public class MyHistoryPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public MyHistoryPagerAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return CarListFragment.newInstance(new ArrayList<Car>());
            case 1:
                return CarListFragment.newInstance(new ArrayList<Car>());
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return mContext.getString(R.string.tab_title_likes);
            case 1:
                return mContext.getString(R.string.tab_title_dislikes);
            default:
                return super.getPageTitle(position);
        }
    }
}
