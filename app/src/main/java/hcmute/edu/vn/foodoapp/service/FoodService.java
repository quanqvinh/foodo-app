package hcmute.edu.vn.foodoapp.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foodoapp.database.DatabaseHelper;
import hcmute.edu.vn.foodoapp.model.Food;

public class FoodService {
    SQLiteDatabase db;
    public List<Food> getAll() {
        List<Food> result = new ArrayList<>();
        db = DatabaseHelper.getInstance().getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from foods", null);
        while (cursor.moveToNext()){
            Food f = new Food(cursor.getInt(0), cursor.getInt(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getInt(6));
            result.add(f);
        }
        return result;
    }
    public List<Food> getByStoreId(Integer storeId) {
        List<Food> result = new ArrayList<>();
        db = DatabaseHelper.getInstance().getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from foods where storeId="+storeId, null);
        while (cursor.moveToNext()){
            Food f = new Food(cursor.getInt(0), cursor.getInt(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getInt(6));
            result.add(f);
        }
        return result;
    }
    public Food getOne(Integer id) {
        db = DatabaseHelper.getInstance().getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from foods where id="+id, null);
        if (cursor.moveToNext()){
            return new Food(cursor.getInt(0), cursor.getInt(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getInt(6));
        }
        return null;
    }
    public void insert(Food food){
        db = DatabaseHelper.getInstance().getWritableDatabase();
        Object[] bindArg = {food.getImage(), food.getName(), food.getType(),
            food.getDescription(), food.getPrice(), food.getStoreId()};
        db.execSQL("insert into foods values (null, ?, ?, ?, ?, ?, ?)", bindArg);
    }
    public int getFoodPrice(int foodId){
        db = DatabaseHelper.getInstance().getWritableDatabase();
        Cursor cursor = db.rawQuery("select price from foods where id="+foodId, null);
        if (cursor.moveToNext())
            return cursor.getInt(0);
        return 0;
    }
    public List<Food> getByKeyword(String keyword) {
        List<Food> result = new ArrayList<>();
        db = DatabaseHelper.getInstance().getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from foods where name like '%"+keyword+"%';", null);
        while (cursor.moveToNext()){
            Food f = new Food(cursor.getInt(0), cursor.getInt(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getInt(6));
            result.add(f);
        }
        return result;
    }
}
