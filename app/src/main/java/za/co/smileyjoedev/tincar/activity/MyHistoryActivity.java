package za.co.smileyjoedev.tincar.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import za.co.smileyjoedev.tincar.R;
import za.co.smileyjoedev.tincar.adapter.MyHistoryPagerAdapter;

/**
 * Created by cody on 2016/08/14.
 */
public class MyHistoryActivity extends BaseActivity {

    private MyHistoryPagerAdapter mPagerHistoryAdapter;

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, MyHistoryActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_history);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        populateView();
    }

    private void populateView(){
        TabLayout tabHistory = (TabLayout) findViewById(R.id.tab_layout_history);
        ViewPager pagerHistory = (ViewPager) findViewById(R.id.viewpager_history);
        mPagerHistoryAdapter = new MyHistoryPagerAdapter(getBaseContext(), getSupportFragmentManager());
        pagerHistory.setAdapter(mPagerHistoryAdapter);
        tabHistory.setupWithViewPager(pagerHistory);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPagerHistoryAdapter.refresh();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
