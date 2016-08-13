package za.co.smileyjoedev.tincar.object;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by cody on 2016/08/13.
 */
public class DbCarObject extends SugarRecord {

    @Unique
    private long mCarId;
    private String mCarJson;
    private int mStatusId;

    public static DbCarObject fromCar(Car car){
        Gson gson = new Gson();
        DbCarObject object = new DbCarObject();
        object.setStatusId(car.getStatusId());
        object.setCarJson(gson.toJson(car));
        object.setCarId(car.getId());
        return object;
    }

    public void setCarJson(String carJson) {
        mCarJson = carJson;
    }

    public void setStatusId(int statusId) {
        mStatusId = statusId;
    }

    public void setCarId(long carId) {
        mCarId = carId;
    }

    public String getCarJson() {
        return mCarJson;
    }

    public int getStatusId() {
        return mStatusId;
    }

    public long getCarId() {
        return mCarId;
    }

    @Override
    public String toString() {
        return "DbCarObject{" +
                "mCarJson='" + mCarJson + '\'' +
                ", mStatusId=" + mStatusId +
                '}';
    }
}
