package za.co.smileyjoedev.tincar.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import za.co.smileyjoedev.tincar.R;
import za.co.smileyjoedev.tincar.backend.Api;
import za.co.smileyjoedev.tincar.backend.ApiCallback;
import za.co.smileyjoedev.tincar.object.Car;

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

            if(json.has("data")){
                JsonElement element = json.get("data");

                if(element.isJsonArray()){
                    ArrayList<Car> cars = Car.fromApiResponse(element.getAsJsonArray());

                    Log.d("TinCar", cars.get(0).toString());
                }
            }
        }

        @Override
        protected void onFail(Exception e) {

        }
    }
}
