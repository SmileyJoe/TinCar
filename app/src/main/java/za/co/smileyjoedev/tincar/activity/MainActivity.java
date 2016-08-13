package za.co.smileyjoedev.tincar.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

import za.co.smileyjoedev.tincar.R;
import za.co.smileyjoedev.tincar.adapter.SwipeCardAdapter;
import za.co.smileyjoedev.tincar.backend.Api;
import za.co.smileyjoedev.tincar.backend.ApiCallback;
import za.co.smileyjoedev.tincar.helper.Dialog;
import za.co.smileyjoedev.tincar.object.Car;
import za.co.smileyjoedev.tincar.object.DbCarObject;

public class MainActivity extends BaseActivity {

    private SwipeCardAdapter mSwipeCardAdapterCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        setupViews();
        Api.getCars(this, new GetCarCallback(this), true);
    }

    private void setupViews(){
        SwipeCarListener listener = new SwipeCarListener();
        mSwipeCardAdapterCar = new SwipeCardAdapter(getBaseContext(), new ArrayList<Car>());
        SwipeFlingAdapterView swipeView = (SwipeFlingAdapterView) findViewById(R.id.swipe_cars);
        swipeView.setAdapter(mSwipeCardAdapterCar);
        swipeView.setFlingListener(listener);
        swipeView.setOnItemClickListener(listener);
    }

    private class SwipeCarListener implements SwipeFlingAdapterView.onFlingListener, SwipeFlingAdapterView.OnItemClickListener{

        private Car mCarRemoved;

        @Override
        public void removeFirstObjectInAdapter() {
            mCarRemoved = mSwipeCardAdapterCar.getItem(0);
            mSwipeCardAdapterCar.remove(0);
        }

        @Override
        public void onLeftCardExit(Object o) {
            mCarRemoved.setStatusId(Car.STATUS_DISLIKED);
            save();
        }

        @Override
        public void onRightCardExit(Object o) {
            mCarRemoved.setStatusId(Car.STATUS_LIKED);
            save();
        }

        @Override
        public void onAdapterAboutToEmpty(int i) {
        }

        @Override
        public void onScroll(float v) {
        }

        @Override
        public void onItemClicked(int position, Object object) {
            Car car = (Car) object;
            startActivity(CarViewActivity.getIntent(getBaseContext(), car));
        }

        private void save(){
            DbCarObject object = DbCarObject.fromCar(mCarRemoved);
            object.save();
        }
    }

    private class GetCarCallback extends ApiCallback{

        private AppCompatActivity mActivity;

        public GetCarCallback(AppCompatActivity activity) {
            mActivity = activity;
        }

        @Override
        protected void onSuccess(JsonObject json) {

            if(json.has("data")){
                JsonElement element = json.get("data");

                if(element.isJsonArray()){
                    ArrayList<Car> cars = Car.fromApiResponse(element.getAsJsonArray());
                    mSwipeCardAdapterCar.update(cars);
                }
            }
        }

        @Override
        protected void onFail(Exception e) {
            Dialog.error(mActivity, R.string.dialog_message_error_general, new ErrorDialogListener()).show();
        }

        private class ErrorDialogListener implements Dialog.Listener{
            @Override
            public void onPositiveClick() {
                finish();
            }

            @Override
            public void onNegativeClick() {

            }
        }
    }
}
