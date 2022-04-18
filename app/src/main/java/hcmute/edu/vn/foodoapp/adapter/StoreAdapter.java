package hcmute.edu.vn.foodoapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import hcmute.edu.vn.foodoapp.R;
import hcmute.edu.vn.foodoapp.model.Store;

public class StoreAdapter extends ArrayAdapter<Store> {
    Context context;
    int layoutId;
    List<Store> data;

    public StoreAdapter(@NonNull Context context, int layoutId, @NonNull List<Store> data) {
        super(context, layoutId, data);
        this.context = context;
        this.layoutId = layoutId;
        this.data = data;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);

        ImageView imgStore = (ImageView) convertView.findViewById(R.id.ivStoreImage);
        TextView txtStoreName = (TextView) convertView.findViewById(R.id.tvStoreName);
        TextView txtStoreFoods = (TextView) convertView.findViewById(R.id.tvStoreFoods);
        Store store = data.get(position);

        imgStore.setImageResource(store.getImage());
        txtStoreName.setText(store.getName());
        txtStoreFoods.setText(store.getListFoods().length() == 0 ? "empty" : store.getListFoods());

        return convertView;
    }
}
