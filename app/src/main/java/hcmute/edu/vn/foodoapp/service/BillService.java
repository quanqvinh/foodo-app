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
import hcmute.edu.vn.foodoapp.model.Store;

public class BillService {
    SQLiteDatabase db;
    public List<BillDetails> getBillDetailsByBillId(Integer billId) {
        List<BillDetails> details = new ArrayList<>();
        FoodService foodService;
        db = DatabaseHelper.getInstance().getReadableDatabase();
        Cursor cursor = db.rawQuery("select bd.id, bd.foodId, bd.billId, bd.amount, bd.price from bill_details bd " +
                "join bills b on bd.billId = b.id " +
                "where b.id = " + billId, null);
        while (cursor.moveToNext()){
            BillDetails aDetails = new BillDetails(cursor.getInt(0), cursor.getInt(1),
                    cursor.getInt(2), cursor.getInt(3), cursor.getInt(4));
            foodService = new FoodService();
            aDetails.setFood(foodService.getOne(cursor.getInt(1)));
            details.add(aDetails);
        }
        cursor.close();
        return details;
    }
    public Bill getBillById(Integer billId) {
        db = DatabaseHelper.getInstance().getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from bills where id = " + billId, null);
        if (cursor.moveToNext()){
            Bill b = new Bill(cursor.getInt(0), cursor.getInt(1), cursor.getString(2),
                    cursor.getInt(3), cursor.getInt(4), cursor.getString(5));
            b.setDetails(getBillDetailsByBillId(billId));
            return b;
        }
        cursor.close();
        return null;
    }

    public List<Bill> getByUserId(Integer userId) {
        List<Bill> bills = new ArrayList<>();
        db = DatabaseHelper.getInstance().getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from bills where userId="+userId, null);
        while (cursor.moveToNext()){
            Bill bill = new Bill(cursor.getInt(0), cursor.getInt(1), cursor.getString(2),
                    cursor.getInt(3), cursor.getInt(4), cursor.getString(5));
            bill.setDetails(getBillDetailsByBillId(cursor.getInt(0)));
            bills.add(bill);
        }
        cursor.close();
        return bills;
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
        values.put("storeId", bill.getStoreId());
        values.put("totalPrice", bill.getTotalPrice());
        return db.insert("bills", null, values);
    }

    public int calculateBillTotalPrice(Bill bill){
        int total = 0;
        for (BillDetails detail : bill.getDetails()) {
            total += detail.getPrice();
        }
        return total;
    }

    public int calculateBillDetailsPrice(BillDetails details){
        return details.getAmount() * details.getFood().getPrice();
    }

}
