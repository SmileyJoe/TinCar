package za.co.smileyjoedev.tincar.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import java.lang.reflect.Array;
import java.util.ArrayList;

import za.co.smileyjoedev.tincar.R;
import za.co.smileyjoedev.tincar.activity.CarViewActivity;
import za.co.smileyjoedev.tincar.object.Car;

/**
 * Created by cody on 2016/08/14.
 */
public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.ViewHolder> {

    private ArrayList<Car> mItems;

    public CarListAdapter(ArrayList<Car> items) {
        mItems = items;
    }

    public void update(ArrayList<Car> cars){
        mItems = cars;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_car, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.render(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mTextTitle;
        private TextView mTextPrice;
        private ImageView mImageCar;
        private Car mCar;

        public ViewHolder(View itemView) {
            super(itemView);
            process(itemView);
            itemView.setOnClickListener(this);
        }

        private void process(View view){
            mTextTitle = (TextView) view.findViewById(R.id.text_title);
            mImageCar = (ImageView) view.findViewById(R.id.image_car);
            mTextPrice = (TextView) view.findViewById(R.id.text_price);
        }

        public void render(Car car){
            mCar = car;
            mTextTitle.setText(car.getTitle());
            mTextPrice.setText(mCar.getAmountNegotiable(mTextPrice.getContext()));

            Ion.with(mImageCar)
                    .load(car.getDefaultImageUrl());
        }

        @Override
        public void onClick(View v) {
            v.getContext().startActivity(CarViewActivity.getIntent(v.getContext(), mCar));
        }
    }

}
