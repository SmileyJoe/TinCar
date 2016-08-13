package za.co.smileyjoedev.tincar.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import za.co.smileyjoedev.tincar.R;

/**
 * Created by cody on 2016/08/13.
 */
public class SocialBarView extends LinearLayout {

    public SocialBarView(Context context) {
        super(context);
        init();
    }

    public SocialBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SocialBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.view_social_bar, this);
    }

    public void setPhoneNumber(String phoneNumber){
        if(!TextUtils.isEmpty(phoneNumber)) {
            ImageView imagePhone = (ImageView) findViewById(R.id.image_phone);
            imagePhone.setVisibility(View.VISIBLE);
            imagePhone.setOnClickListener(new OnPhoneClick(phoneNumber));
        }
    }

    public void setWebUrl(String url){
        if(!TextUtils.isEmpty(url)){
            ImageView imageWeb = (ImageView) findViewById(R.id.image_web);
            imageWeb.setVisibility(View.VISIBLE);
            imageWeb.setOnClickListener(new OnWebClick(url));
        }
    }

    public void setShareText(int shareDialogTitleResId, String shareText){
        if(!TextUtils.isEmpty(shareText)){
            ImageView imageShare = (ImageView) findViewById(R.id.image_share);
            imageShare.setVisibility(View.VISIBLE);
            imageShare.setOnClickListener(new OnShareClick(shareDialogTitleResId, shareText));
        }
    }

    public void setEmail(int chooserTitleResId, String emailAddress, String message, String subject){
        if(!TextUtils.isEmpty(emailAddress)){
            ImageView imageEmail = (ImageView) findViewById(R.id.image_email);
            imageEmail.setVisibility(View.VISIBLE);
            imageEmail.setOnClickListener(new OnEmailClick(chooserTitleResId, emailAddress, message, subject));
        }
    }

    private class OnEmailClick implements View.OnClickListener{

        private int mChooserTitleResId;
        private String mEmail;
        private String mMessage;
        private String mSubject;

        public OnEmailClick(int chooserTitleResId, String email, String message, String subject) {
            mChooserTitleResId = chooserTitleResId;
            mEmail = email;
            mMessage = message;
            mSubject = subject;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/html");
            intent.putExtra(Intent.EXTRA_EMAIL, mEmail);
            intent.putExtra(Intent.EXTRA_SUBJECT, mSubject);
            intent.putExtra(Intent.EXTRA_TEXT, mMessage);

            v.getContext().startActivity(Intent.createChooser(intent, v.getContext().getString(mChooserTitleResId)));
        }
    }

    private class OnShareClick implements View.OnClickListener{

        private String mShareText;
        private int mShareDialogTitleResId;

        public OnShareClick(int shareDialogTitleResId, String shareText) {
            mShareText = shareText;
            mShareDialogTitleResId = shareDialogTitleResId;
        }

        @Override
        public void onClick(View v) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, mShareText);
            v.getContext().startActivity(Intent.createChooser(sharingIntent, v.getContext().getString(mShareDialogTitleResId)));
        }
    }

    private class OnWebClick implements View.OnClickListener{

        private String mUrl;

        public OnWebClick(String url) {
            mUrl = url;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(mUrl));
            v.getContext().startActivity(intent);
        }
    }

    private class OnPhoneClick implements View.OnClickListener{

        private String mPhone;

        public OnPhoneClick(String phone) {
            mPhone = phone;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + mPhone));
            v.getContext().startActivity(intent);
        }
    }
}
