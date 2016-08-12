package za.co.smileyjoedev.tincar.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

import za.co.smileyjoedev.tincar.R;
import za.co.smileyjoedev.tincar.object.Car;

/**
 * Created by cody on 2016/08/11.
 */
public class SwipeCardAdapter extends BaseAdapter {

    private ArrayList<Car> mItems = new ArrayList<>();
    private Context mContext;

    public SwipeCardAdapter(Context context, ArrayList<Car> items) {
        mContext = context;
        mItems = items;
    }

    public void update(ArrayList<Car> cars){
        mItems = cars;
        notifyDataSetChanged();
    }

    public void remove(int position){
        mItems.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Car getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.swipe_card_item_car, parent, false);

            holder = new ViewHolder();
            holder.process(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.render(getItem(position));

        return convertView;
    }

    private static class ViewHolder{
        private TextView mTextTitle;
        private TextView mTextAmount;
        private ImageView mImageCar;

        public void process(View view){
            mTextTitle = (TextView) view.findViewById(R.id.text_title);
            mImageCar = (ImageView) view.findViewById(R.id.image_car);
            mTextAmount = (TextView) view.findViewById(R.id.text_amount);
        }

        public void render(Car car){
            mTextTitle.setText(car.getTitle());
            mTextAmount.setText(car.getAmount().getFormatted());

            Ion.with(mImageCar)
                    .placeholder(android.R.drawable.btn_star_big_off)
                    .error(android.R.drawable.btn_star_big_on)
                    .load(car.getDefaultImageUrl());
        }
    }
}
