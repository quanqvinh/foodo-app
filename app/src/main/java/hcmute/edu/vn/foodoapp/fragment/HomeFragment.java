package hcmute.edu.vn.foodoapp.fragment;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import hcmute.edu.vn.foodoapp.R;
import hcmute.edu.vn.foodoapp.activity.MainActivity;
import hcmute.edu.vn.foodoapp.activity.StoreActivity;
import hcmute.edu.vn.foodoapp.adapter.FoodAdapter;
import hcmute.edu.vn.foodoapp.adapter.StoreAdapter;
import hcmute.edu.vn.foodoapp.model.Food;
import hcmute.edu.vn.foodoapp.model.Store;
import hcmute.edu.vn.foodoapp.service.FoodService;
import hcmute.edu.vn.foodoapp.service.StoreService;

public class HomeFragment extends Fragment {

    final public static String EXTRA_MESSAGE = "Data tu anh Vinh phun so tec";

    ListView lvStores;
    ListView lvFoods;
    List<Store> dataStore;
    List<Food> dataFood;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        dataStore = MainActivity.storeService.getAll();
        StoreAdapter storeAdapter = new StoreAdapter(getActivity(), R.layout.store_item_layout, dataStore);
        lvStores = (ListView) view.findViewById(R.id.lvStores);
        lvStores.setAdapter(storeAdapter);
        expandListView(lvStores);

        lvStores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), StoreActivity.class);
                intent.putExtra(EXTRA_MESSAGE, dataStore.get(i));
                startActivity(intent);
            }
        });


        dataFood = MainActivity.foodService.getAll();
        FoodAdapter foodAdapter = new FoodAdapter(getActivity(), R.layout.food_item_layout, dataFood);
        lvFoods = view.findViewById(R.id.lvFoods);
        lvFoods.setAdapter(foodAdapter);
        expandListView(lvFoods);

        lvFoods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Store store = MainActivity.storeService.getOne(dataFood.get(i).getStoreId());
                Log.d("Vinh", store.getFoods() == null ? "NULL" : "OK");
                Intent intent = new Intent(getActivity(), StoreActivity.class);
                intent.putExtra(EXTRA_MESSAGE, store);
                startActivity(intent);
            }
        });

        return view;
    }

    public void expandListView(ListView lv) {
        ListAdapter adapter = lv.getAdapter();
        if (adapter.getCount() == 0)
            return;

        int height = lv.getPaddingBottom() + lv.getPaddingTop();
        View item = adapter.getView(0, null, lv);
        item.measure(0, 0);
        height += adapter.getCount() * (item.getMeasuredHeight() * 0.88);

        height += lv.getDividerHeight() * (adapter.getCount() - 1);

        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int heightDp = Math.round(height / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        lv.getLayoutParams().height = heightDp;
    }
}