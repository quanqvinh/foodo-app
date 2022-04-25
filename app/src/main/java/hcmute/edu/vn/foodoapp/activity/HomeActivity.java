package hcmute.edu.vn.foodoapp.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

import hcmute.edu.vn.foodoapp.R;
import hcmute.edu.vn.foodoapp.adapter.FoodAdapter;
import hcmute.edu.vn.foodoapp.adapter.StoreAdapter;
import hcmute.edu.vn.foodoapp.database.DatabaseHelper;
import hcmute.edu.vn.foodoapp.model.Food;
import hcmute.edu.vn.foodoapp.model.Store;
import hcmute.edu.vn.foodoapp.service.FoodService;
import hcmute.edu.vn.foodoapp.service.StoreService;

public class HomeActivity extends AppCompatActivity {

    ListView lvStores;
    ListView lvFoods;
    List<Store> dataStore;
    List<Food> dataFood;
    StoreService storeService;
    FoodService foodService;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        storeService = new StoreService();
        dataStore = storeService.getAll();
        StoreAdapter storeAdapter = new StoreAdapter(this, R.layout.store_item_layout, dataStore);
        lvStores = (ListView) findViewById(R.id.lvStores);
        lvStores.setAdapter(storeAdapter);

        foodService = new FoodService();
        dataFood = foodService.getAll();
        FoodAdapter foodAdapter = new FoodAdapter(this, R.layout.food_item_layout, dataFood);
        lvFoods = (ListView) findViewById(R.id.lvFoods);
        lvFoods.setAdapter(foodAdapter);

    }


}