package za.co.smileyjoedev.tincar.object;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import za.co.smileyjoedev.tincar.helper.DateHelper;
import za.co.smileyjoedev.tincar.helper.JsonHelper;

/**
 * Created by cody on 2016/08/10.
 */
public class Car {

    private long mId;
    private String mTitle;
    private HashMap<Extra.Type, Extra> mExtras;
    private Date mCreatedAt;
    private Date mUpdatedAt;
    private Date mEnableAt;
    private Date mDisableAt;

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

    public void setCreatedAt(String apiDate){
        mCreatedAt = getDate(apiDate);
    }

    public String getCreatedAt(){
        return getDate(mCreatedAt);
    }

    public void setUpdatedAt(String apiDate){
        mUpdatedAt = getDate(apiDate);
    }

    public String getUpdatedAt(){
        return getDate(mUpdatedAt);
    }

    public void setEnableAt(String apiDate){
        mEnableAt = getDate(apiDate);
    }

    public String getEnableAt(){
        return getDate(mEnableAt);
    }

    public void setDisableAt(String apiDate){
        mDisableAt = getDate(apiDate);
    }

    public String getDisableAt(){
        return getDate(mDisableAt);
    }

    private Date getDate(String apiDate){
        return DateHelper.format(apiDate, DateHelper.FORMAT_API);
    }

    private String getDate(Date date){
        return DateHelper.format(date);
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
        car.setCreatedAt(helper.getString("created_at"));
        car.setUpdatedAt(helper.getString("updated_at"));
        car.setEnableAt(helper.getString("enable_at"));
        car.setDisableAt(helper.getString("disable_at"));

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
                ", mCreatedAt=" + getCreatedAt() +
                ", mUpdatedAt=" + getUpdatedAt() +
                ", mEnableAt=" + getEnableAt() +
                ", mDisableAt=" + getDisableAt() +
                '}';
    }
}
