package za.co.smileyjoedev.tincar.object;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.JsonObject;

import za.co.smileyjoedev.tincar.R;
import za.co.smileyjoedev.tincar.helper.JsonHelper;

/**
 * Created by cody on 2016/08/10.
 */
public class Extra {

    public enum Type{

        MAKE(R.string.extra_title_make, "make"),
        MODEL(R.string.extra_title_model, "model"),
        LOCATION(R.string.extra_title_location, "location"),
        LISTING_STATUS(R.string.extra_title_listing_status, "listing_status"),
        ADVERTISER(R.string.extra_title_advertiser, "advertiser"),
        DUTY(R.string.extra_title_duty, "duty"),
        DRIVE_TYPE(R.string.extra_title_drive_type, "drive_type"),
        DRIVE_SETUP(R.string.extra_title_drive_setup, "drive_setup"),
        BODY_TYPE(R.string.extra_title_body_type, "body_type"),
        CONDITION(R.string.extra_title_condition, "condition"),
        FUEL_TYPE(R.string.extra_title_fuel_type, "fuel_type"),
        TRANSMISSION(R.string.extra_title_transmission, "transmission"),
        INTERIOR(R.string.extra_title_interior, "interior"),
        COLOUR(R.string.extra_title_colour, "colour"),
        DOOR_COUNT(R.string.extra_title_door_count, "door_count");

        private String mDisplayTitle;
        private int mDisplayTitleResId;
        private String mApiKey;

        Type(int displayTitleResId, String apiKey) {
            mDisplayTitleResId = displayTitleResId;
            mApiKey = apiKey;
        }

        public String getDisplayTitle(Context context) {
            if(TextUtils.isEmpty(mDisplayTitle)){
                mDisplayTitle = context.getString(mDisplayTitleResId);
            }

            return mDisplayTitle;
        }

        public String getApiKey() {
            return mApiKey;
        }
    }

    private long mId;
    private String mTitle;

    public void setId(long id) {
        mId = id;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public long getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public static Extra fromApiResponse(JsonObject object){
        JsonHelper helper = new JsonHelper(object);
        Extra extra = new Extra();

        extra.setId(helper.getLong("id"));
        extra.setTitle(helper.getString("title"));

        return extra;
    }

    @Override
    public String toString() {
        return "Extra{" +
                "mId=" + mId +
                ", mTitle='" + mTitle + '\'' +
                '}';
    }
}
