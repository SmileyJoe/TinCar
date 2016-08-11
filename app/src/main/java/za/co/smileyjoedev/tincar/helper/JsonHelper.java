package za.co.smileyjoedev.tincar.helper;

import com.google.gson.JsonObject;

/**
 * Created by cody on 2016/08/10.
 */
public class JsonHelper {

    private JsonObject mObject;

    public static final String DEFAULT_STRING = "";
    public static final long DEFAULT_LONG = -1L;
    public static final int DEFAULT_INT = -1;
    public static final double DEFAULT_DOUBLE = -1.0;
    public static final boolean DEFAULT_BOOLEAN = false;

    public JsonHelper(JsonObject object) {
        mObject = object;
    }

    public String getString(String key){
        String returnString = DEFAULT_STRING;

        if(isValid(key)){
            try{
                returnString = mObject.get(key).getAsString();
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        return returnString;
    }

    public long getLong(String key){
        long returnLong = DEFAULT_LONG;

        if(isValid(key)){
            try{
                returnLong = mObject.get(key).getAsLong();
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        return returnLong;
    }

    public JsonObject getObject(String key){
        JsonObject returnObject = null;

        if(isValid(key)){
            try{
                returnObject = mObject.get(key).getAsJsonObject();
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        return returnObject;
    }

    public double getDouble(String key){
        double value = DEFAULT_DOUBLE;

        if(isValid(key)){
            try{
                value = mObject.get(key).getAsDouble();
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        return value;
    }

    public boolean getBoolean(String key){
        boolean value = DEFAULT_BOOLEAN;

        if(isValid(key)){
            try{
                value = mObject.get(key).getAsBoolean();
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        return value;
    }

    public int getInt(String key){
        int value = DEFAULT_INT;

        if(isValid(key)){
            try{
                value = mObject.get(key).getAsInt();
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        return value;
    }

    private boolean isValid(String key){
        if(mObject != null && mObject.has(key)) {
            return true;
        } else {
            return false;
        }
    }
}
