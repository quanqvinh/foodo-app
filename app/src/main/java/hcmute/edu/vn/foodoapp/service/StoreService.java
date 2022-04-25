package hcmute.edu.vn.foodoapp.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foodoapp.database.DatabaseHelper;
import hcmute.edu.vn.foodoapp.model.Food;
import hcmute.edu.vn.foodoapp.model.Store;

public class StoreService {
    SQLiteDatabase db;
    FoodService foodService;
    public List<Store> getAll() {
        List<Store> result = new ArrayList<>();
        db = DatabaseHelper.getInstance().getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from stores", null);
        while (cursor.moveToNext()){
            foodService = new FoodService();
            List<Food> listFood = foodService.getByStoreId(cursor.getInt(0));
            Store s = new Store(cursor.getInt(0), cursor.getInt(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5), listFood);
            result.add(s);
        }
        return result;
    }

    public Store getOne(Integer id) {
        db = DatabaseHelper.getInstance().getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from stores where id="+id, null);
        if (cursor.moveToNext()){
            return new Store(cursor.getInt(0), cursor.getInt(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5));
        }
        return null;
    }

    public List<Food> getFoodsFromStore(Integer storeId) {
        List<Food> result = new ArrayList<>();
        db = DatabaseHelper.getInstance().getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from foods where storeId="+storeId, null);
        if (cursor.moveToNext()){
            Food f = new Food(cursor.getInt(0), cursor.getInt(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getInt(6));
            result.add(f);
        }
        return result;
    }
    public void insert(Store store){
        db = DatabaseHelper.getInstance().getWritableDatabase();
        Object[] bindArg = {store.getImage(), store.getName(), store.getOpenAt(),
                store.getCloseAt(), store.getDescription()};
        db.execSQL("insert into stores values (null, ?, ?, ?, ?, ?)", bindArg);
    }

}
