package za.co.smileyjoedev.tincar.object;

import android.content.Context;
import android.text.TextUtils;

import za.co.smileyjoedev.tincar.R;

/**
 * Created by cody on 2016/08/10.
 */
public class Extra {

    public static enum Type{

        MAKE(R.string.extra_title_make, "make");

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

}
