package hcmute.edu.vn.foodoapp.adapter;

import hcmute.edu.vn.foodoapp.R;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;

import java.util.List;

import hcmute.edu.vn.foodoapp.activity.CartActivity;
import hcmute.edu.vn.foodoapp.activity.MainActivity;
import hcmute.edu.vn.foodoapp.model.BillDetails;
import hcmute.edu.vn.foodoapp.model.Food;

public class BillDetailAdapter extends ArrayAdapter<BillDetails> {

    Context context;
    int layout;
    List<BillDetails> data;

    public BillDetailAdapter(@NonNull Context context, int resource, @NonNull List<BillDetails> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.data = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(layout, null);

        BillDetails detail = data.get(position);

        ImageView ivFoodImage = convertView.findViewById(R.id.ivFoodImage);
        TextView tvFoodName = convertView.findViewById(R.id.tvFoodName);
        TextView tvQuantity = convertView.findViewById(R.id.tvQuantity);
        TextView tvPrice = convertView.findViewById(R.id.tvPrice);
        ImageButton btnReduce = convertView.findViewById(R.id.btnReduce);
        ImageButton btnAdd = convertView.findViewById(R.id.btnAdd);

        Food food = MainActivity.foodService.getOne(detail.getFoodId());
        ivFoodImage.setImageResource(food.getImage());
        tvFoodName.setText(food.getName());
        tvQuantity.setText(detail.getAmount() + "");
        tvPrice.setText(detail.getPriceWithMoneyFormat());

        btnReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int price = detail.getPrice() / detail.getAmount();
                if (detail.getAmount() == 1)
                    data.remove(position);
                else {
                    detail.setAmount(detail.getAmount() - 1);
                    detail.setPrice(detail.getPrice() - price);
                }
                ((CartActivity)getContext()).refreshPrice(position);
                notifyDataSetChanged();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int price = detail.getPrice() / detail.getAmount();
                detail.setAmount(detail.getAmount() + 1);
                detail.setPrice(detail.getPrice() + price);
                ((CartActivity)getContext()).refreshPrice(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}
