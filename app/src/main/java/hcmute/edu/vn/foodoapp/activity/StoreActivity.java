package hcmute.edu.vn.foodoapp.activity;

import hcmute.edu.vn.foodoapp.R;
import hcmute.edu.vn.foodoapp.adapter.FoodAdapter;
import hcmute.edu.vn.foodoapp.fragment.HomeFragment;
import hcmute.edu.vn.foodoapp.model.Bill;
import hcmute.edu.vn.foodoapp.model.BillDetails;
import hcmute.edu.vn.foodoapp.model.Food;
import hcmute.edu.vn.foodoapp.model.Store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class StoreActivity extends AppCompatActivity {

    final public static String MESSAGE = "Dau lung vl";

    ImageView ivStoreImage;
    TextView tvStoreName;
    TextView tvStoreAddress;
    TextView tvOpenedTime;
    ListView lvStoreFood;
    Button btnCheckout;
    public TextView amountInCart;
    public TextView tvTotalBill;
    public ConstraintLayout clCart;

    Store store;
    public Bill bill;
    public List<BillDetails> listFoods;

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
        amountInCart = findViewById(R.id.amountInCart);
        tvTotalBill = findViewById(R.id.tvTotalBill);
        clCart = findViewById(R.id.clCart);
        clCart.getLayoutParams().height = 1;

        store = (Store) getIntent().getSerializableExtra(HomeFragment.EXTRA_MESSAGE);
        bill = new Bill(null, MainActivity.userId, "", store.getId(), 0, MainActivity.userService.getOne(MainActivity.userId).getAddress());
        listFoods = new ArrayList<BillDetails>();

        setInformation(store);
        lvStoreFood.setAdapter(new FoodAdapter(this, R.layout.store_food_item_layout, store.getFoods()));
        expandListView(lvStoreFood);

        btnCheckout.setOnClickListener(this::checkoutHandler);

        List<Food> listFoods = store.getFoods();
        for (Food f:listFoods) {
            Log.d("ALO", f.getName());
        }

    }

    private void checkoutHandler(View view){
        Intent intent = new Intent(StoreActivity.this, CartActivity.class);
//        bill = fakeData();
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("hh:mmaa MM/dd/yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Saigon"));
        String strDate = dateFormat.format(date);
        bill.setCreatedAt(strDate);
        bill.setDetails(listFoods);
        intent.putExtra(MESSAGE, bill);
        startActivity(intent);
    }

    private void setInformation(Store store) {
        ivStoreImage.setImageResource(store.getImage());
        tvStoreName.setText(store.getName());
        tvStoreAddress.setText(store.getAddress());
        tvOpenedTime.setText(store.getOpenAt() + " - " + store.getCloseAt());
        amountInCart.setText("0");
        tvTotalBill.setText("0đ");
    }

//    private Bill fakeData() {
//        Bill bill = new Bill(2, 1, "27/04/2022 06:11 AM", 1, 124000, "Ở đâu đó");
//
//        List<BillDetails> billDetails = new ArrayList<BillDetails>();
//        billDetails.add(new BillDetails(1, 1, 2, 2, 64000));
//        billDetails.add(new BillDetails(2, 2, 2, 1, 45000));
//
//        bill.setDetails(billDetails);
//        return bill;
//    }

    public void expandListView(ListView lv) {
        ListAdapter adapter = lv.getAdapter();
        if (adapter.getCount() == 0)
            return;

        int height = lv.getPaddingBottom() + lv.getPaddingTop();
        View item = adapter.getView(0, null, lv);
        item.measure(0, 0);
        height += adapter.getCount() * (item.getMeasuredHeight() * 0.8);

        height += lv.getDividerHeight() * (adapter.getCount() - 1);

        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
        height = Math.round(height / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        lv.getLayoutParams().height = height;
    }
}