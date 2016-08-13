package za.co.smileyjoedev.tincar.object;

import com.google.gson.Gson;
import com.orm.SugarRecord;

/**
 * Created by cody on 2016/08/13.
 */
public class DbCarObject extends SugarRecord {

    private String mCarJson;
    private int mStatusId;

    public static DbCarObject fromCar(Car car){
        Gson gson = new Gson();
        DbCarObject object = new DbCarObject();
        object.setStatusId(car.getStatusId());
        object.setCarJson(gson.toJson(car));
        return object;
    }

    public void setCarJson(String carJson) {
        mCarJson = carJson;
    }

    public void setStatusId(int statusId) {
        mStatusId = statusId;
    }

    public String getCarJson() {
        return mCarJson;
    }

    public int getStatusId() {
        return mStatusId;
    }

    @Override
    public String toString() {
        return "DbCarObject{" +
                "mCarJson='" + mCarJson + '\'' +
                ", mStatusId=" + mStatusId +
                '}';
    }
}
