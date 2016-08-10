package za.co.smileyjoedev.tincar.backend;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import za.co.smileyjoedev.tincar.R;
import za.co.smileyjoedev.tincar.helper.Dialog;

/**
 * Created by cody on 2016/08/10.
 */
public class Api {

    private static final String BASE_URL = "http://empty-bush-3943.getsandbox.com/";

    private static final String API_CAR_LISTINGS = BASE_URL + "listings";

    public static void getCars(Activity activity, ApiCallback callback, boolean showDialog){
        call(activity, API_CAR_LISTINGS, R.string.text_type_cars, callback, showDialog);
    }

    private static void call(Activity activity, String url, int typeResId, ApiCallback callback, boolean showDialog){
        if(showDialog) {
            ProgressDialog dialog = Dialog.loading(activity, typeResId);
            dialog.show();
            callback.setProgressDialog(dialog);
        }

        Ion.with(activity)
                .load(url)
                .asJsonObject()
                .setCallback(callback);
    }

}
