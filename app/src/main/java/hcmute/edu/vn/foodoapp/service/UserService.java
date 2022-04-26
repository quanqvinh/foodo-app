package hcmute.edu.vn.foodoapp.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import hcmute.edu.vn.foodoapp.database.DatabaseHelper;
import hcmute.edu.vn.foodoapp.model.User;

public class UserService {
    SQLiteDatabase db;
    public int authenticate(String username, String password) {
        db = DatabaseHelper.getInstance().getReadableDatabase();
        String[] selectionArgs = {username, password};
        Cursor cursor = db.rawQuery("select id from users where username=? and password=?", selectionArgs);
        if (cursor.moveToNext()){
            return cursor.getInt(0);
        }
        cursor.close();
        return -1;
    }
    public User getOne(int userId) {
        db = DatabaseHelper.getInstance().getReadableDatabase();
        Cursor cursor = db.rawQuery("select username, password, address, phone " +
                "from users where id="+userId, null);
        if (cursor.moveToNext()){
            return new User(userId, cursor.getString(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3));
        }
        cursor.close();
        return null;
    }
    public void insert(User user) {
        db = DatabaseHelper.getInstance().getWritableDatabase();
        Object[] bindArgs = {user.getUsername(), user.getPassword(), user.getAddress(), user.getPhone()};
        db.execSQL("insert into users values (null, ?, ?, ?, ?)", bindArgs);
    }
}
