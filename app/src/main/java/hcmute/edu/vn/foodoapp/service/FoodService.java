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
    public List<Food> getAll(Context context) {
        List<Food> result = new ArrayList<>();
        SQLiteDatabase db = DatabaseHelper.getInstance(context).getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from foods", null);
        while (cursor.moveToNext()){
            Food f = new Food(cursor.getInt(0), cursor.getInt(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getInt(6));
            result.add(f);
        }
        return result;
    }
    public Food getOne(Context context, Integer id) {
        SQLiteDatabase db = DatabaseHelper.getInstance(context).getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from foods where id="+id, null);
        if (cursor.moveToNext()){
            return new Food(cursor.getInt(0), cursor.getInt(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getInt(6));
        }
        return null;
    }
}
