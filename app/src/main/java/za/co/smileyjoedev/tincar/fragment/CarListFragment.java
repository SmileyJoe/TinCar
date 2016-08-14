package za.co.smileyjoedev.tincar.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import za.co.smileyjoedev.tincar.R;
import za.co.smileyjoedev.tincar.object.Car;

/**
 * Created by cody on 2016/08/14.
 */
public class CarListFragment extends Fragment {

    private static final String ARGUMENT_CARS = "cars";

    private ArrayList<Car> mCars = new ArrayList<>();

    public static CarListFragment newInstance(ArrayList<Car> cars) {
        CarListFragment fragment = new CarListFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARGUMENT_CARS, cars);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();

        if(arguments != null){
            if(arguments.containsKey(ARGUMENT_CARS)){
                mCars = (ArrayList<Car>) arguments.getSerializable(ARGUMENT_CARS);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_car_list, container, false);
    }

}
