package za.co.smileyjoedev.tincar.object;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;

import za.co.smileyjoedev.tincar.helper.JsonHelper;

/**
 * Created by cody on 2016/08/10.
 */
public class Car {

    private long mId;
    private String mTitle;
    private HashMap<Extra.Type, Extra> mExtras;

    public void setId(long id) {
        mId = id;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void addExtra(Extra.Type type, Extra extra){
        if(mExtras == null){
            mExtras = new HashMap<>();
        }

        mExtras.put(type, extra);
    }

    public long getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public Extra getExtra(Extra.Type type){
        Extra extra = null;

        if(mExtras != null){
            if(mExtras.containsKey(type)){
                extra = mExtras.get(type);
            }
        }

        return extra;
    }

    public static ArrayList<Car> fromApiResponse(JsonArray jsonArray){
        ArrayList<Car> cars = new ArrayList<>();

        for(int i = 0; i < jsonArray.size(); i++){
            JsonElement element = jsonArray.get(i);

            if(element.isJsonObject()) {
                cars.add(fromApiResponse(element.getAsJsonObject()));
            }
        }

        return cars;
    }

    public static Car fromApiResponse(JsonObject object){
        Car car = new Car();
        JsonHelper helper = new JsonHelper(object);

        car.setId(helper.getLong("id"));
        car.setTitle(helper.getString("title"));

        for(Extra.Type type:Extra.Type.values()){
            car.addExtra(type, Extra.fromApiResponse(helper.getObject(type.getApiKey())));
        }

        return car;
    }

    @Override
    public String toString() {
        return "Car{" +
                "mId=" + mId +
                ", mTitle='" + mTitle + '\'' +
                ", mExtras=" + mExtras +
                '}';
    }
}
