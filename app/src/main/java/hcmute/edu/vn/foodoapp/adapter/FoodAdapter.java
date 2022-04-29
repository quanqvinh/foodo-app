package hcmute.edu.vn.foodoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.time.Instant;
import java.util.List;

import hcmute.edu.vn.foodoapp.R;
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

        ImageView ivFoodImage = (ImageView) convertView.findViewById(R.id.ivFoodImage);
        TextView tvFoodName = (TextView) convertView.findViewById(R.id.tvFoodItemName);
        TextView tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.tvPrice);

        Food food = data.get(position);

        ivFoodImage.setImageResource(food.getImage());
        tvFoodName.setText(food.getName());
        tvDescription.setText(food.getDescription());
        tvPrice.setText(food.getPriceWithMoneyFormat());

        return convertView;
    }
}
