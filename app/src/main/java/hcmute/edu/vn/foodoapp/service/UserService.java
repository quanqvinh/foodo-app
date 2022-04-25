package hcmute.edu.vn.foodoapp.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import hcmute.edu.vn.foodoapp.database.DatabaseHelper;
import hcmute.edu.vn.foodoapp.model.User;

public class UserService {
    SQLiteDatabase db;
    public boolean isAuthenticated(String username, String password) {
        db = DatabaseHelper.getInstance().getReadableDatabase();
        String[] selectionArgs = {username, password};
        Cursor cursor = db.rawQuery("select id from users where username=? and password=?", selectionArgs);
        if (cursor.moveToNext()){
            return  true;
        }
        cursor.close();
        return false;
    }
    public void insert(User user) {
        db = DatabaseHelper.getInstance().getWritableDatabase();
        Object[] bindArgs = {user.getUsername(), user.getPassword(), user.getAddress(), user.getPhone()};
        db.execSQL("insert into users values (null, ?, ?, ?, ?)", bindArgs);
    }
}
