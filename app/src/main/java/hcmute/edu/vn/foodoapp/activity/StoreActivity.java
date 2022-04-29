package hcmute.edu.vn.foodoapp.activity;

import hcmute.edu.vn.foodoapp.R;
import hcmute.edu.vn.foodoapp.adapter.FoodAdapter;
import hcmute.edu.vn.foodoapp.fragment.HomeFragment;
import hcmute.edu.vn.foodoapp.model.Bill;
import hcmute.edu.vn.foodoapp.model.BillDetails;
import hcmute.edu.vn.foodoapp.model.Store;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StoreActivity extends AppCompatActivity {

    final public static String MESSAGE = "Dau lung vl";

    ImageView ivStoreImage;
    TextView tvStoreName;
    TextView tvStoreAddress;
    TextView tvOpenedTime;
    ListView lvStoreFood;
    Button btnCheckout;

    Store store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_activity);

        ivStoreImage = findViewById(R.id.ivStoreImage);
        tvStoreName = findViewById(R.id.tvStoreName);
        tvStoreAddress = findViewById(R.id.tvStoreAddress);
        tvOpenedTime = findViewById(R.id.tvOpenedTime);
        lvStoreFood = findViewById(R.id.lvStoreFood);
        btnCheckout = findViewById(R.id.btnCheckOut);

        store = (Store) getIntent().getSerializableExtra(HomeFragment.EXTRA_MESSAGE);

        setInformation(store);
//        Log.d("Vinh", store.getFoods() == null ? "NULL" : "OK");
        lvStoreFood.setAdapter(new FoodAdapter(this, R.layout.store_food_item_layout, store.getFoods()));

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StoreActivity.this, CartActivity.class);
                Bill bill = fakeData();
                intent.putExtra(MESSAGE, bill);
                startActivity(intent);
            }
        });
    }

    private void setInformation(Store store) {
        ivStoreImage.setImageResource(store.getImage());
        tvStoreName.setText(store.getName());
        tvStoreAddress.setText(store.getAddress());
        tvOpenedTime.setText(store.getOpenAt() + " - " + store.getCloseAt());
    }

    private Bill fakeData() {
        Bill bill = new Bill(2, 1, "27/04/2022 06:11 AM", 1, 124000, "Ở đâu đó");

        List<BillDetails> billDetails = new ArrayList<BillDetails>();
        billDetails.add(new BillDetails(1, 1, 2, 2, 64000));
        billDetails.add(new BillDetails(2, 2, 2, 1, 45000));

        bill.setDetails(billDetails);
        return bill;
    }
}