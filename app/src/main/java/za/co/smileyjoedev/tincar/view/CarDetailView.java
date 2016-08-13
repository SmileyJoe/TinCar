package za.co.smileyjoedev.tincar.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import za.co.smileyjoedev.tincar.R;

/**
 * Created by cody on 2016/08/13.
 */
public class CarDetailView extends LinearLayout {

    private TextView mTextTitle;
    private TextView mTextContent;

    public CarDetailView(Context context) {
        super(context);
        init();
    }

    public CarDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CarDetailView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.view_car_detail, this);

        mTextTitle = (TextView) findViewById(R.id.text_title);
        mTextContent = (TextView) findViewById(R.id.text_content);
    }

    public void setTitle(String title){
        mTextTitle.setText(title);
        mTextTitle.setVisibility(View.VISIBLE);
    }

    public void setContent(String content){
        mTextContent.setText(content);
        mTextContent.setVisibility(View.VISIBLE);
    }
}
