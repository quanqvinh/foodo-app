package hcmute.edu.vn.foodoapp.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foodoapp.database.DatabaseHelper;
import hcmute.edu.vn.foodoapp.model.Bill;
import hcmute.edu.vn.foodoapp.model.BillDetails;

public class BillService {
    public List<BillDetails> getBillDetailsByBillId(Context context, Integer billId) {
        List<BillDetails> details = new ArrayList<>();
        SQLiteDatabase db = DatabaseHelper.getInstance(context).getReadableDatabase();
        Cursor cursor = db.rawQuery("select bd.id, bd.foodId, bd.billId, bd.amount, bd.price from bill_details bd " +
                "join bills b on bd.id = b.detailsId " +
                "where b.id = " + billId, null);
        while (cursor.moveToNext()){
            BillDetails aDetails = new BillDetails(cursor.getInt(0), cursor.getInt(1),
                    cursor.getInt(2), cursor.getInt(3), cursor.getInt(4));
            details.add(aDetails);
        }
        return details;
    }
    public Bill getBillById(Context context, Integer billId) {
        SQLiteDatabase db = DatabaseHelper.getInstance(context).getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from bills where id = " + billId, null);
        if (cursor.moveToNext()){
            Bill b = new Bill(cursor.getInt(0), cursor.getInt(1), cursor.getString(2));
            b.setDetails(getBillDetailsByBillId(context, billId));
            return b;
        }
        return null;
    }
}
