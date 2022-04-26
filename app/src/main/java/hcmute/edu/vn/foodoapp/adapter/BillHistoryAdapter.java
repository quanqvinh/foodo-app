package hcmute.edu.vn.foodoapp.adapter;
import hcmute.edu.vn.foodoapp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import hcmute.edu.vn.foodoapp.model.Bill;
import hcmute.edu.vn.foodoapp.model.BillDetails;
import hcmute.edu.vn.foodoapp.service.BillService;
import hcmute.edu.vn.foodoapp.service.FoodService;
import hcmute.edu.vn.foodoapp.service.StoreService;

public class BillHistoryAdapter extends ArrayAdapter<Bill> {

    private Context context;
    private int layout;
    private List<Bill> data;

    public BillHistoryAdapter(@NonNull Context context, int layout, @NonNull List<Bill> data) {
        super(context, layout, data);
        this.context = context;
        this.layout = layout;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(layout, null);

        Bill bill = data.get(position);

        TextView tvCreatedTime = convertView.findViewById(R.id.tvCreatedTime);
        ImageView ivStoreImage = convertView.findViewById(R.id.ivStoreImage);
        TextView tvStoreName = convertView.findViewById(R.id.tvStoreName);
        TextView tvTotalPrice = convertView.findViewById(R.id.tvTotalPrice);
        TextView tvBillDetail = convertView.findViewById(R.id.tvBillDetail);

        StoreService storeService = new StoreService();

        tvCreatedTime.setText(bill.getCreatedAt());
        ivStoreImage.setImageResource(R.drawable.store1);
        tvStoreName.setText("Gà rán KFC");
        tvTotalPrice.setText("120.000 đ");
        tvBillDetail.setText(getBillDetail(bill));

        return convertView;
    }

    private String getBillDetail(Bill bill) {
        String detail = "";
        List<BillDetails> billDetails = bill.getDetails();

        FoodService foodService = new FoodService();

        for (int i = 0; i < billDetails.size(); i++) {
            detail += billDetails.get(i).getAmount() + " x ";
            detail += foodService.getOne(billDetails.get(i).getFoodId()).getName() + " : ";
            detail += billDetails.get(i).getPriceWithMoneyFormat() + "\n";
        }

        return detail;
    }
}
