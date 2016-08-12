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
import za.co.smileyjoedev.tincar.object.Car;

public class MainActivity extends BaseActivity {

    private SwipeCardAdapter mSwipeCardAdapterCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        setupViews();
        Api.getCars(this, new GetCarCallback(), true);
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
            Log.d("TinCar", "remove");
            mCarRemoved = mSwipeCardAdapterCar.getItem(0);
            mSwipeCardAdapterCar.remove(0);
        }

        @Override
        public void onLeftCardExit(Object o) {
            Log.d("TinCar", "left: " + mCarRemoved.getTitle());
        }

        @Override
        public void onRightCardExit(Object o) {
            Log.d("TinCar", "right: " + mCarRemoved.getTitle());
        }

        @Override
        public void onAdapterAboutToEmpty(int i) {
            Log.d("TinCar", "empty");
        }

        @Override
        public void onScroll(float v) {
            Log.d("TinCar", "scroll");
        }

        @Override
        public void onItemClicked(int position, Object object) {
            Car car = (Car) object;
            startActivity(CarViewActivity.getIntent(getBaseContext(), car));
            Log.d("TinCar", "Clicked: " + car.getTitle());
        }
    }

    private class GetCarCallback extends ApiCallback{

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

        }
    }
}
