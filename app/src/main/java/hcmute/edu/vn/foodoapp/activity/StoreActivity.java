package hcmute.edu.vn.foodoapp.activity;

import hcmute.edu.vn.foodoapp.R;
import hcmute.edu.vn.foodoapp.adapter.FoodAdapter;
import hcmute.edu.vn.foodoapp.fragment.HomeFragment;
import hcmute.edu.vn.foodoapp.model.Store;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class StoreActivity extends AppCompatActivity {

    ImageView ivStoreImage;
    TextView tvStoreName;
    TextView tvStoreAddress;
    TextView tvOpenedTime;
    ListView lvStoreFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_activity);

        ivStoreImage = findViewById(R.id.ivStoreImage);
        tvStoreName = findViewById(R.id.tvStoreName);
        tvStoreAddress = findViewById(R.id.tvStoreAddress);
        tvOpenedTime = findViewById(R.id.tvOpenedTime);
        lvStoreFood = findViewById(R.id.lvStoreFood);

        Store store = (Store) getIntent().getSerializableExtra(HomeFragment.EXTRA_MESSAGE);

        setInformation(store);
        lvStoreFood.setAdapter(new FoodAdapter(this, R.layout.store_food_item_layout, store.getFoods()));
    }

    private void setInformation(Store store) {
        ivStoreImage.setImageResource(store.getImage());
        tvStoreName.setText(store.getName());
        tvStoreAddress.setText(store.getAddress());
        tvOpenedTime.setText(store.getOpenAt() + " - " + store.getCloseAt());
    }
}