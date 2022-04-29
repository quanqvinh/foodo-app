package hcmute.edu.vn.foodoapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.w3c.dom.Text;

import java.time.Instant;
import java.util.List;

import hcmute.edu.vn.foodoapp.R;
import hcmute.edu.vn.foodoapp.activity.MainActivity;
import hcmute.edu.vn.foodoapp.activity.SearchActivity;
import hcmute.edu.vn.foodoapp.activity.StoreActivity;
import hcmute.edu.vn.foodoapp.animation.SlideAnimation;
import hcmute.edu.vn.foodoapp.model.Bill;
import hcmute.edu.vn.foodoapp.model.BillDetails;
import hcmute.edu.vn.foodoapp.model.Food;

public class FoodAdapter extends ArrayAdapter<Food> {
    private Context context;
    private int layoutId;
    private List<Food> data;

    public FoodAdapter(@NonNull Context context, int layoutId, @NonNull List<Food> data) {
        super(context, layoutId, data);
        this.context = context;
        this.layoutId = layoutId;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);

        ImageView ivFoodImage =  convertView.findViewById(R.id.ivFoodImage);
        TextView tvFoodName = convertView.findViewById(R.id.tvFoodItemName);
        TextView tvDescription = convertView.findViewById(R.id.tvDescription);
        TextView tvPrice = convertView.findViewById(R.id.tvPrice);
        ImageButton btnAddToCart = convertView.findViewById(R.id.btnAddToCart);
        Food food = data.get(position);

        ivFoodImage.setImageResource(food.getImage());
        tvFoodName.setText(food.getName());
        tvDescription.setText(food.getDescription());
        tvPrice.setText(food.getPriceWithMoneyFormat());

        if (btnAddToCart != null)
            btnAddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ConstraintLayout clCart = ((StoreActivity)getContext()).clCart;
                    TextView amountInCart = ((StoreActivity)getContext()).amountInCart;
                    TextView tvTotalBill = ((StoreActivity)getContext()).tvTotalBill;
                    List<BillDetails> listDetail = ((StoreActivity) getContext()).listFoods;
                    Bill bill = ((StoreActivity)getContext()).bill;
                    Food food = data.get(position);

//                    Log.d("Vinh", "Before " + bill.getTotalPriceWithMoneyFormat() + " " + listDetail.size());

                    if (Integer.parseInt(amountInCart.getText().toString()) == 0) {
//                        bill = new Bill(null, MainActivity.userId, "", MainActivity.storeService.getOne(food.getStoreId()).getId(), 0, MainActivity.userService.getOne(MainActivity.userId).getAddress());
                        listDetail.add(new BillDetails(null, food.getId(), null, 1, food.getPrice()));
                        amountInCart.setText("1");

                        Animation animation = new SlideAnimation(clCart, 1, 150);
                        animation.setInterpolator(new AccelerateInterpolator());
                        animation.setDuration(200);
                        clCart.setAnimation(animation);
                        clCart.startAnimation(animation);
                    }
                    else {
                        boolean isInCart = false;
                        for (BillDetails bd : listDetail)
                            if (food.getId() == bd.getFoodId()) {
                                bd.addAmount();
                                amountInCart.setText(Integer.parseInt(amountInCart.getText().toString()) + 1 + "");
                                bd.setPrice(bd.getPrice() + food.getPrice());
                                isInCart = true;
                                break;
                            }

                        if (!isInCart) {
                            listDetail.add(new BillDetails(null, food.getId(), null, 1, food.getPrice()));
                            amountInCart.setText(Integer.parseInt(amountInCart.getText().toString()) + 1 + "");
                        }
                    }

                    bill.setTotalPrice(bill.getTotalPrice() + food.getPrice());
                    tvTotalBill.setText(bill.getTotalPriceWithMoneyFormat());
//                    Log.d("Vinh", "After " + bill.getTotalPriceWithMoneyFormat() + " " + listDetail.size());
                }
            });

        return convertView;
    }
}
