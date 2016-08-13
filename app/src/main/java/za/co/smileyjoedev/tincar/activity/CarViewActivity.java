package za.co.smileyjoedev.tincar.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.koushikdutta.ion.Ion;

import za.co.smileyjoedev.tincar.R;
import za.co.smileyjoedev.tincar.object.Car;
import za.co.smileyjoedev.tincar.object.Extra;
import za.co.smileyjoedev.tincar.view.CarDetailView;
import za.co.smileyjoedev.tincar.view.SocialBarView;

/**
 * Created by cody on 2016/08/12.
 */
public class CarViewActivity extends BaseActivity {

    public static final String INTENT_EXTRA_CAR = "car";

    private Car mCar;
    private LinearLayout mLayoutContent;

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

            ImageView imageCar = (ImageView) findViewById(R.id.image_car);
            SocialBarView socialCar = (SocialBarView) findViewById(R.id.social_car);
            mLayoutContent = (LinearLayout) findViewById(R.id.layout_content_wrapper);

            String subject = getString(R.string.text_email_subject, mCar.getUrl());
            String message = getString(R.string.text_email_message, mCar.getUrl());

            socialCar.setPhoneNumber(mCar.getUser().getMobileNumber());
            socialCar.setWebUrl(mCar.getUrl());
            socialCar.setShareText(R.string.dialog_share_title, getString(R.string.text_share, mCar.getUrl()));
            socialCar.setEmail(R.string.dialog_share_title, mCar.getUser().getEmail(), message, subject);

            Ion.with(imageCar).load(mCar.getDefaultImageUrl());

            String amount = mCar.getAmount().getFormatted();

            if(mCar.isNegotiable()){
                amount += " " + getString(R.string.car_detail_extra_is_negotiable);
            }

            addDetail(R.string.car_detail_title_amount, amount);
            addDetail(R.string.car_detail_title_description, mCar.getDescription());
            addDetail(R.string.car_detail_title_engine_size, mCar.getEngineSize());
            addDetail(R.string.car_detail_title_year, Integer.toString(mCar.getYear()));
            addDetail(R.string.car_detail_title_mileage, mCar.getEngineSize());
            addDetail(R.string.car_detail_title_registration, mCar.getRegistration());
            addDetail(R.string.car_detail_title_money_back_guarantee, Boolean.toString(mCar.isMoneyBackGuarantee()));

            for(Extra.Type type:Extra.Type.values()){
                Extra extra = mCar.getExtra(type);

                if(extra != null){
                    addDetail(type.getDisplayTitle(getBaseContext()), extra.getTitle());
                }
            }

        }
    }

    private void addDetail(int titleResId, String content){
        addDetail(getString(titleResId), content);
    }

    private void addDetail(String title, String content){
        if(!TextUtils.isEmpty(content)) {
            CarDetailView view = new CarDetailView(getBaseContext());
            view.setContent(content);
            view.setTitle(title);
            mLayoutContent.addView(view);
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
