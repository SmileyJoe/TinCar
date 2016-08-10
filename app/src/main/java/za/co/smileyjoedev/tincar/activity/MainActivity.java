package za.co.smileyjoedev.tincar.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.JsonObject;

import za.co.smileyjoedev.tincar.R;
import za.co.smileyjoedev.tincar.backend.Api;
import za.co.smileyjoedev.tincar.backend.ApiCallback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Api.getCars(this, new GetCarCallback(), true);
    }

    private class GetCarCallback extends ApiCallback{

        @Override
        protected void onSuccess(JsonObject json) {
            Log.d("TinCar", json.toString());
        }

        @Override
        protected void onFail(Exception e) {

        }
    }
}
