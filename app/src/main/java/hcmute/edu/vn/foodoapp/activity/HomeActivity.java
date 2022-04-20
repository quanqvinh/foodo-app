package hcmute.edu.vn.foodoapp.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foodoapp.R;
import hcmute.edu.vn.foodoapp.adapter.FoodAdapter;
import hcmute.edu.vn.foodoapp.adapter.StoreAdapter;
import hcmute.edu.vn.foodoapp.model.Food;
import hcmute.edu.vn.foodoapp.model.Store;

public class HomeActivity extends AppCompatActivity {

    ListView lvStores;
    ListView lvFoods;
    List<Store> dataStore;
    List<Food> dataFood;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        fakeData();

        StoreAdapter storeAdapter = new StoreAdapter(this, R.layout.store_item_layout, dataStore);
        lvStores = (ListView) findViewById(R.id.lvStores);
        lvStores.setAdapter(storeAdapter);

        FoodAdapter foodAdapter = new FoodAdapter(this, R.layout.food_item_layout, dataFood);
        lvFoods = (ListView) findViewById(R.id.lvFoods);
        lvFoods.setAdapter(foodAdapter);


    }

    private void fakeData() {
        dataStore = new ArrayList<Store>();
        Store kfc = new Store(null, R.drawable.store1,"Gà rán KFC", "7:00", "22:00", "Gà rán siu ngon");
        kfc.addFood(null, R.drawable.food1, "Gà rán 1", "Đồ ăn nhanh", "1 đùi hoặc miếng gà rán", 32000);
        kfc.addFood(null, R.drawable.food1, "Gà rán 2", "Đồ ăn nhanh", "2 đùi hoặc miếng gà rán", 54000);
        kfc.addFood(null, R.drawable.food1, "Combo 1", "Đồ ăn nhanh", "1 đùi gà, 1 khoai tây chiên, 1 nước", 45000);
        kfc.addFood(null, R.drawable.food1, "Combo 2", "Đồ ăn nhanh", "2 đùi gà, 1 khoai tây chiên, 1 nước", 88000);

        Store domino = new Store(null, R.drawable.store2, "Pizza Domino","7:00", "22:00", "Pizza siu ngon");
        domino.addFood(null, R.drawable.food2, "Gà rán 1", "Đồ ăn nhanh", "1 đùi hoặc miếng gà rán", 32000);
        domino.addFood(null, R.drawable.food2, "Gà rán 2", "Đồ ăn nhanh", "2 đùi hoặc miếng gà rán", 54000);
        domino.addFood(null, R.drawable.food2, "Combo 1", "Đồ ăn nhanh", "1 đùi gà, 1 khoai tây chiên, 1 nước", 45000);
        domino.addFood(null, R.drawable.food2, "Combo 2", "Đồ ăn nhanh", "2 đùi gà, 1 khoai tây chiên, 1 nước", 88000);
        dataStore.add(kfc);
        dataStore.add(domino);

        dataFood = kfc.getFoods();
        for (int i = 0; i < domino.getFoods().size(); i++)
            dataFood.add(kfc.getFoods().get(i));
    }
}