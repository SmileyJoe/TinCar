package za.co.smileyjoedev.tincar.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;

import com.koushikdutta.ion.Ion;

import za.co.smileyjoedev.tincar.R;
import za.co.smileyjoedev.tincar.object.Car;

/**
 * Created by cody on 2016/08/12.
 */
public class CarViewActivity extends BaseActivity {

    public static final String INTENT_EXTRA_CAR = "car";

    private Car mCar;

    public static Intent getIntent(Context context, Car car){
        Intent intent = new Intent(context, CarViewActivity.class);
        intent.putExtra(INTENT_EXTRA_CAR, car);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_view);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        handleExtras();
        populateView();
    }

    private void handleExtras(){
        Intent intent = getIntent();

        if(intent != null) {
            Bundle extras = intent.getExtras();

            if (extras != null) {
                if (extras.containsKey(INTENT_EXTRA_CAR)) {
                    mCar = (Car) extras.getSerializable(INTENT_EXTRA_CAR);
                    Log.d("TinCar", "Car: " + mCar.getTitle());
                }
            }
        }

        if(mCar == null){
            // TODO show error dialog and close activity on click
        }
    }

    private void populateView(){
        if(mCar != null){
            getSupportActionBar().setTitle(mCar.getTitle());
            getSupportActionBar().setSubtitle(mCar.getAmount().getFormatted());

            ImageView imageCar = (ImageView) findViewById(R.id.image_car);

            Ion.with(imageCar).load(mCar.getDefaultImageUrl());
        }
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
