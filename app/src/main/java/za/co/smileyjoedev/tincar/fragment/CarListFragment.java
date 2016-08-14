package za.co.smileyjoedev.tincar.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import za.co.smileyjoedev.tincar.R;
import za.co.smileyjoedev.tincar.adapter.CarListAdapter;
import za.co.smileyjoedev.tincar.object.Car;

/**
 * Created by cody on 2016/08/14.
 */
public class CarListFragment extends Fragment {

    private static final String ARGUMENT_CARS = "cars";
    private static final String ARGUMENT_POSITION = "position";

    private int mPosition = 0;

    private CarListAdapter mCarListAdapter;

    public static CarListFragment newInstance(ArrayList<Car> cars, int position) {
        CarListFragment fragment = new CarListFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARGUMENT_CARS, cars);
        args.putInt(ARGUMENT_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    public int getPosition() {
        return mPosition;
    }

    public void update(ArrayList<Car> cars){
        mCarListAdapter.update(cars);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();

        if(arguments != null){
            if(arguments.containsKey(ARGUMENT_CARS)){
                mCarListAdapter = new CarListAdapter((ArrayList<Car>) arguments.getSerializable(ARGUMENT_CARS));
            }

            if(arguments.containsKey(ARGUMENT_POSITION)){
                mPosition = arguments.getInt(ARGUMENT_POSITION);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_car_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(mCarListAdapter == null || mCarListAdapter.getItemCount() == 0){
            TextView textEmpty = (TextView) view.findViewById(R.id.text_empty);
            textEmpty.setVisibility(View.VISIBLE);
            textEmpty.setText(R.string.text_empty_car_list);
        } else {
            RecyclerView recyclerCars = (RecyclerView) view.findViewById(R.id.recycler_cars);
            recyclerCars.setVisibility(View.VISIBLE);
            recyclerCars.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerCars.setAdapter(mCarListAdapter);
        }
    }
}
