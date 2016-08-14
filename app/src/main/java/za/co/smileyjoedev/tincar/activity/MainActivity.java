package za.co.smileyjoedev.tincar.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

import za.co.smileyjoedev.tincar.R;
import za.co.smileyjoedev.tincar.adapter.SwipeCardAdapter;
import za.co.smileyjoedev.tincar.backend.Api;
import za.co.smileyjoedev.tincar.backend.ApiCallback;
import za.co.smileyjoedev.tincar.helper.Dialog;
import za.co.smileyjoedev.tincar.object.Car;
import za.co.smileyjoedev.tincar.object.DbCarObject;

public class MainActivity extends BaseActivity {

    private SwipeCardAdapter mSwipeCardAdapterCar;
    private SwipeFlingAdapterView mSwipeView;

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
        mSwipeView = (SwipeFlingAdapterView) findViewById(R.id.swipe_cars);
        mSwipeView.setAdapter(mSwipeCardAdapterCar);
        mSwipeView.setFlingListener(listener);
        mSwipeView.setOnItemClickListener(listener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_view_my_history:
                startActivity(MyHistoryActivity.getIntent(getBaseContext()));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case CarViewActivity.INTENT_REQUEST_ID:
                if(resultCode == RESULT_OK){
                    // TODO: Remove the top card from the adapter //
                }
                return;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                return;
        }
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
            startActivityForResult(CarViewActivity.getIntent(getBaseContext(), car), CarViewActivity.INTENT_REQUEST_ID);
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
                    ArrayList<Car> cars = Car.fromApiResponse(element.getAsJsonArray(), true);
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
