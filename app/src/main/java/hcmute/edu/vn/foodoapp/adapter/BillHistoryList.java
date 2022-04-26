package hcmute.edu.vn.foodoapp.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import java.util.List;

import hcmute.edu.vn.foodoapp.R;
import hcmute.edu.vn.foodoapp.model.Bill;

public class BillHistoryList extends ListFragment {

    List<Bill> data;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        setListAdapter(new BillHistoryAdapter(getActivity(), R.layout.bill_history_item, data));

        return inflater.inflate(R.layout.bill_history_list, container, false);
    }
}
