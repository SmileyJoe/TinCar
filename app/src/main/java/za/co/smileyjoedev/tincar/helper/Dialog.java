package za.co.smileyjoedev.tincar.helper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;

import za.co.smileyjoedev.tincar.R;

/**
 * Created by cody on 2016/08/10.
 */
public class Dialog {

    public interface Listener{
        public void onPositiveClick();
        public void onNegativeClick();
    }

    public static ProgressDialog loading(Activity activity, int loadingTypeResId){
        String loadingType = activity.getString(loadingTypeResId);
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(activity.getString(R.string.dialog_message_loading, loadingType));
        return progressDialog;
    }

    public static AlertDialog.Builder error(Activity activity, int messageResId, Listener listener){
        DialogListener dialogListener = new DialogListener(listener);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.dialog_title_error);
        builder.setMessage(messageResId);
        builder.setPositiveButton(R.string.dialog_button_ok, dialogListener);
        return builder;
    }

    public static class DialogListener implements DialogInterface.OnClickListener{

        private Listener mListener;

        public DialogListener(Listener listener) {
            mListener = listener;
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            if(mListener != null){
                switch (which){
                    case AlertDialog.BUTTON_POSITIVE:
                        mListener.onPositiveClick();
                        break;
                    case AlertDialog.BUTTON_NEGATIVE:
                        mListener.onNegativeClick();
                        break;
                }
            }
        }
    }

}
