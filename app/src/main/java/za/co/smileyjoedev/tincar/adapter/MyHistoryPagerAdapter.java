package za.co.smileyjoedev.tincar.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;

import za.co.smileyjoedev.tincar.R;
import za.co.smileyjoedev.tincar.fragment.CarListFragment;
import za.co.smileyjoedev.tincar.helper.DbHelper;
import za.co.smileyjoedev.tincar.object.Car;

/**
 * Created by cody on 2016/08/14.
 */
public class MyHistoryPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private ArrayList<Integer> mPositionsToRefresh;

    public MyHistoryPagerAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return CarListFragment.newInstance(getCarsForPosition(position), position);
    }

    private ArrayList<Car> getCarsForPosition(int position){
        switch (position) {
            case 0:
                return DbHelper.getLikedCars();
            case 1:
                return DbHelper.getDislikedCars();
            default:
                return null;
        }
    }

    @Override
    public int getItemPosition(Object object) {
        if(object instanceof CarListFragment){
            CarListFragment fragment = (CarListFragment) object;
            int position = fragment.getPosition();

            if(mPositionsToRefresh.contains(position)){
                mPositionsToRefresh.remove(new Integer(fragment.getPosition()));
                fragment.update(getCarsForPosition(position));
            }
        }

        return super.getItemPosition(object);
    }

    public void refresh(){
        mPositionsToRefresh = new ArrayList<>();
        mPositionsToRefresh.add(0);
        mPositionsToRefresh.add(1);
        notifyDataSetChanged();
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
