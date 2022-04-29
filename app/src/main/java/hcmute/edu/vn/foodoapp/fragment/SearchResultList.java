package hcmute.edu.vn.foodoapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import java.util.List;

import hcmute.edu.vn.foodoapp.R;
import hcmute.edu.vn.foodoapp.activity.MainActivity;
import hcmute.edu.vn.foodoapp.adapter.BillHistoryAdapter;
import hcmute.edu.vn.foodoapp.model.Bill;
import hcmute.edu.vn.foodoapp.model.Food;

public class SearchResultList extends ListFragment {
    List<Food> data;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        String keyword = container.getTag().toString();
//        data = MainActivity.foodService.getByKeyword(keyword);
//
//        setListAdapter(new BillHistoryAdapter(getActivity(), R.layout.bill_history_item, data));

        return inflater.inflate(R.layout.bill_history_list, container, false);
    }
}
