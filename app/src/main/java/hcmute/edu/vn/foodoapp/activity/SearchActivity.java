package hcmute.edu.vn.foodoapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import hcmute.edu.vn.foodoapp.R;
import hcmute.edu.vn.foodoapp.adapter.FoodAdapter;
import hcmute.edu.vn.foodoapp.fragment.HomeFragment;
import hcmute.edu.vn.foodoapp.model.Food;
import hcmute.edu.vn.foodoapp.model.Store;

public class SearchActivity extends AppCompatActivity {
    final public static String MESSAGE = "alo dang mo qua day ghi choi vai con lo";

    EditText etSearchInput;
    ListView lvSearchList;
    List<Food> data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        etSearchInput = findViewById(R.id.etSearchInput);
        lvSearchList = findViewById(R.id.lvSearchList);

        String keyword = getIntent().getStringExtra(MainActivity.MESSAGE);

        Toast.makeText(this, keyword, Toast.LENGTH_SHORT).show();

        data = MainActivity.foodService.getByKeyword(keyword);

        lvSearchList.setAdapter(new FoodAdapter(this, R.layout.food_item_layout, data));

        lvSearchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Store store = MainActivity.storeService.getOne(data.get(i).getStoreId());
                Intent intent = new Intent(getApplicationContext(), StoreActivity.class);
                intent.putExtra(HomeFragment.EXTRA_MESSAGE, store);
                startActivity(intent);
            }
        });
    }
}
