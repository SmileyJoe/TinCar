package za.co.smileyjoedev.tincar.backend;

import android.app.ProgressDialog;
import android.util.Log;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;

/**
 * Created by cody on 2016/08/10.
 */
public abstract class ApiCallback implements FutureCallback<JsonObject> {

    protected abstract void onSuccess(JsonObject json);
    protected abstract void onFail(Exception e);
    private ProgressDialog mProgressDialog;

    public void setProgressDialog(ProgressDialog progressDialog) {
        mProgressDialog = progressDialog;
    }

    @Override
    public void onCompleted(Exception e, JsonObject result) {
        hideProgressDialog();
        if(result != null){
            onSuccess(result);
        } else {
            if(e == null){
                e = new NullPointerException();
            }

            onFail(e);
        }
    }

    public void hideProgressDialog(){
        if(mProgressDialog != null && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }
}
