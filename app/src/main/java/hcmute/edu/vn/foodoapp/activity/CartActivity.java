package hcmute.edu.vn.foodoapp.activity;

import hcmute.edu.vn.foodoapp.R;
import hcmute.edu.vn.foodoapp.adapter.BillDetailAdapter;
import hcmute.edu.vn.foodoapp.model.Bill;
import hcmute.edu.vn.foodoapp.model.BillDetails;
import hcmute.edu.vn.foodoapp.model.User;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    TextView etNameAndPhone;
    TextView etRecipients;
    TextView tvCreatedTime;
    ListView lvFoodInCart;
    TextView tvStoreName;
    TextView tvSumPortion;
    TextView tvSumPricePortion;
    TextView tvShippingCost;
    TextView tvTotalPrice;
    Button btnCheckout;
    BillDetailAdapter billDetailAdapter;

    Bill bill;

    int shippingCost = 15000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_activity);

        etNameAndPhone = findViewById(R.id.etNameAndPhone);
        etRecipients = findViewById(R.id.etRecipients);
        tvCreatedTime = findViewById(R.id.tvCreatedTime);
        tvStoreName = findViewById(R.id.tvStoreName);
        tvSumPortion = findViewById(R.id.tvSumPortion);
        tvSumPricePortion = findViewById(R.id.tvSumPricePortion);
        tvShippingCost = findViewById(R.id.tvShippingCost);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        btnCheckout = findViewById(R.id.btnCheckout);
        lvFoodInCart = findViewById(R.id.lvFoodInCart);

        bill = (Bill) getIntent().getSerializableExtra(StoreActivity.MESSAGE);
        billDetailAdapter = new BillDetailAdapter(this, R.layout.bill_detail_item, bill.getDetails());
        lvFoodInCart.setAdapter(billDetailAdapter);
        billDetailAdapter.notifyDataSetChanged();


        setData();

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bill.setTotalPrice(bill.getTotalPrice() + shippingCost);
                MainActivity.billService.insertBillWithDetails(bill);

                Toast.makeText(getApplicationContext(), "Đặt đơn thành công!", Toast.LENGTH_SHORT)
                        .show();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                int currentUserId = getIntent().getIntExtra(LoginActivity.USERID_AUTHENTICATED_MESSAGE, 1);
                intent.putExtra(LoginActivity.USERID_AUTHENTICATED_MESSAGE, currentUserId);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void setData() {
        int sumPortion = 0, sumPrice = 0;

        for(BillDetails detail : bill.getDetails()) {
            sumPortion += detail.getAmount();
            sumPrice += detail.getPrice();
        }

        int shippingCost = 15000;
        int totalCostIncludeShipping = shippingCost + bill.getTotalPrice();
        User user = MainActivity.userService.getOne(bill.getUserId());
        etNameAndPhone.setText(user.getUsername() + " - " + user.getPhone());
        etRecipients.setText(user.getAddress());
        tvCreatedTime.setText("  Thời gian đặt - " + bill.getCreatedAt());
        tvStoreName.setText("  " + MainActivity.storeService.getOne(bill.getStoreId()).getName());
        tvSumPortion.setText("Tổng (" + sumPortion + " phần)");
        tvSumPricePortion.setText(bill.getTotalPriceWithMoneyFormat());
        tvShippingCost.setText(getMoneyFormat(shippingCost));
        tvTotalPrice.setText(getMoneyFormat(totalCostIncludeShipping));
        btnCheckout.setText("Đặt đơn - " + getMoneyFormat(totalCostIncludeShipping));
    }

    private String getMoneyFormat(int price) {
        String s = price + "";
        String result = " đ";
        int i;
        for (i = s.length() - 3; i > 0; i -= 3)
            result = "." + s.substring(i, i + 3) + result;
        if (i <= 0)
            result = s.substring(0, i + 3) + result;
        return result.charAt(0) == '.' ? result.substring(1) : result;
    }

    @SuppressLint("SetTextI18n")
    public void refreshPrice(int i) {
        int sumPortion = 0, sumPrice = 0;

        for(BillDetails detail : bill.getDetails()) {
            sumPortion += detail.getAmount();
            sumPrice += detail.getPrice();
        }

        int totalCostIncludeShipping = shippingCost + sumPrice;
        bill.setTotalPrice(sumPrice);

        tvSumPortion.setText("Tổng (" + sumPortion + " phần)");
        tvSumPricePortion.setText(bill.getTotalPriceWithMoneyFormat());
        tvShippingCost.setText(getMoneyFormat(shippingCost));
        tvTotalPrice.setText(getMoneyFormat(totalCostIncludeShipping));
        btnCheckout.setText("Đặt đơn - " + getMoneyFormat(totalCostIncludeShipping));
    }
}