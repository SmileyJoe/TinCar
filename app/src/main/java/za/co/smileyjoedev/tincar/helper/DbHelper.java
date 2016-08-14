package za.co.smileyjoedev.tincar.helper;

import java.util.ArrayList;
import java.util.List;

import za.co.smileyjoedev.tincar.object.Car;
import za.co.smileyjoedev.tincar.object.DbCarObject;

/**
 * Created by cody on 2016/08/14.
 */
public class DbHelper {

    public static ArrayList<Long> getHistoryIds(){
        ArrayList<Long> historyIds = new ArrayList<>();
        List<DbCarObject> objects = DbCarObject.listAll(DbCarObject.class);

        for(DbCarObject object:objects){
            historyIds.add(object.getCarId());
        }

        return historyIds;
    }

    public static ArrayList<Car> getLikedCars(){
        List<DbCarObject> objects = DbCarObject.
                findWithQuery(DbCarObject.class,
                        "SELECT * FROM DB_CAR_OBJECT WHERE M_STATUS_ID = ?",
                        Integer.toString(Car.STATUS_LIKED));

        return Car.fromDbCarObject(objects);
    }

    public static ArrayList<Car> getDislikedCars(){
        List<DbCarObject> objects = DbCarObject.
                findWithQuery(DbCarObject.class,
                        "SELECT * FROM DB_CAR_OBJECT WHERE M_STATUS_ID = ?",
                        Integer.toString(Car.STATUS_DISLIKED));

        return Car.fromDbCarObject(objects);
    }

}
