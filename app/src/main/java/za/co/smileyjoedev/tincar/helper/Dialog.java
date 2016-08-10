package za.co.smileyjoedev.tincar.helper;

import android.app.Activity;
import android.app.ProgressDialog;

import za.co.smileyjoedev.tincar.R;

/**
 * Created by cody on 2016/08/10.
 */
public class Dialog {

    public static ProgressDialog loading(Activity activity, int loadingTypeResId){
        String loadingType = activity.getString(loadingTypeResId);
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(activity.getString(R.string.dialog_message_loading, loadingType));
        return progressDialog;
    }

}
