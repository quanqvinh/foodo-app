package hcmute.edu.vn.foodoapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import hcmute.edu.vn.foodoapp.R;
import hcmute.edu.vn.foodoapp.adapter.FoodAdapter;
import hcmute.edu.vn.foodoapp.adapter.StoreAdapter;
import hcmute.edu.vn.foodoapp.model.Food;
import hcmute.edu.vn.foodoapp.model.Store;
import hcmute.edu.vn.foodoapp.service.FoodService;
import hcmute.edu.vn.foodoapp.service.StoreService;

public class HomeFragment extends Fragment {

    ListView lvStores;
    ListView lvFoods;
    List<Store> dataStore;
    List<Food> dataFood;
    StoreService storeService;
    FoodService foodService;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        storeService = new StoreService();
        dataStore = storeService.getAll();
        StoreAdapter storeAdapter = new StoreAdapter(getActivity(), R.layout.store_item_layout, dataStore);
        lvStores = (ListView) view.findViewById(R.id.lvStores);
        lvStores.setAdapter(storeAdapter);

        foodService = new FoodService();
        dataFood = foodService.getAll();
        FoodAdapter foodAdapter = new FoodAdapter(getActivity(), R.layout.food_item_layout, dataFood);
        lvFoods = (ListView) view.findViewById(R.id.lvFoods);
        lvFoods.setAdapter(foodAdapter);

        return view;
    }
}