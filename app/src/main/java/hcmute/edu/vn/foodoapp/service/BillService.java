package hcmute.edu.vn.foodoapp.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foodoapp.database.DatabaseHelper;
import hcmute.edu.vn.foodoapp.model.Bill;
import hcmute.edu.vn.foodoapp.model.BillDetails;

public class BillService {
    SQLiteDatabase db;
    public List<BillDetails> getBillDetailsByBillId(Integer billId) {
        List<BillDetails> details = new ArrayList<>();
        db = DatabaseHelper.getInstance().getReadableDatabase();
        Cursor cursor = db.rawQuery("select bd.id, bd.foodId, bd.billId, bd.amount, bd.price from bill_details bd " +
                "join bills b on bd.id = b.detailsId " +
                "where b.id = " + billId, null);
        while (cursor.moveToNext()){
            BillDetails aDetails = new BillDetails(cursor.getInt(0), cursor.getInt(1),
                    cursor.getInt(2), cursor.getInt(3), cursor.getInt(4));
            details.add(aDetails);
        }
        cursor.close();
        return details;
    }
    public Bill getBillById(Integer billId) {
        db = DatabaseHelper.getInstance().getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from bills where id = " + billId, null);
        if (cursor.moveToNext()){
            Bill b = new Bill(cursor.getInt(0), cursor.getInt(1), cursor.getString(2));
            b.setDetails(getBillDetailsByBillId(billId));
            return b;
        }
        cursor.close();
        return null;
    }

    public void insertBillWithDetails(Bill bill){
        List<BillDetails> billDetails = bill.getDetails();
        if (billDetails != null) {
            long insertedBillId = insertBillOnly(bill);
            if (insertedBillId > -1) {
                for (BillDetails details : billDetails) {
                    details.setBillId((int)insertedBillId);
                    insertBillDetails(details);
                }
            }
        }
    }

    private void insertBillDetails(BillDetails details){
        db = DatabaseHelper.getInstance().getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("foodId", details.getFoodId());
        values.put("billId", details.getBillId());
        values.put("amount", details.getAmount());
        values.put("price", details.getPrice());
        db.insert("bill_details", null, values);
    }
    private long insertBillOnly(Bill bill) {
        db = DatabaseHelper.getInstance().getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userId", bill.getUserId());
        values.put("createdAt", bill.getCreatedAt());
        return db.insert("bills", null, values);
    }

}
