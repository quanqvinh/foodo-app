package hcmute.edu.vn.foodoapp.activity;

import hcmute.edu.vn.foodoapp.R;
import hcmute.edu.vn.foodoapp.adapter.BillDetailAdapter;
import hcmute.edu.vn.foodoapp.model.Bill;
import hcmute.edu.vn.foodoapp.model.BillDetails;
import hcmute.edu.vn.foodoapp.model.User;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    TextView tvNameAndPhone;
    TextView tvRecipients;
    TextView tvCreatedTime;
    ListView lvFoodInCart;
    TextView tvStoreName;
    TextView tvSumPortion;
    TextView tvSumPricePortion;
    TextView tvShippingCost;
    TextView tvTotalPrice;
    Button btnCheckout;

    Bill bill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_activity);

        tvNameAndPhone = findViewById(R.id.tvNameAndPhone);
        tvRecipients = findViewById(R.id.tvRecipients);
        tvCreatedTime = findViewById(R.id.tvCreatedTime);
        tvStoreName = findViewById(R.id.tvStoreName);
        tvSumPortion = findViewById(R.id.tvSumPortion);
        tvSumPricePortion = findViewById(R.id.tvSumPricePortion);
        tvShippingCost = findViewById(R.id.tvShippingCost);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        btnCheckout = findViewById(R.id.btnCheckout);
        lvFoodInCart = findViewById(R.id.lvFoodInCart);

        bill = (Bill) getIntent().getSerializableExtra(StoreActivity.MESSAGE);
        lvFoodInCart.setAdapter(new BillDetailAdapter(this, R.layout.bill_detail_item, bill.getDetails()));

        setData();
    }

    private void setData() {
        int sumPortion = 0, sumPrice = 0;

        for(BillDetails detail : bill.getDetails()) {
            sumPortion += detail.getAmount();
            sumPrice += detail.getPrice();
        }

        User user = MainActivity.userService.getOne(bill.getUserId());
        tvNameAndPhone.setText(user.getUsername() + " - " + user.getPhone());
        tvRecipients.setText(user.getAddress());
        tvCreatedTime.setText("  Thời gian đặt - " + bill.getCreatedAt());
        tvStoreName.setText("  " + MainActivity.storeService.getOne(bill.getStoreId()).getName());
        tvSumPortion.setText("Tổng (" + sumPortion + " phần)");
        tvSumPricePortion.setText("109.000đ");
        tvShippingCost.setText("15.000đ");
        tvTotalPrice.setText(bill.getTotalPriceWithMoneyFormat());
        btnCheckout.setText("Đặt đơn - " + bill.getTotalPriceWithMoneyFormat());
    }
}