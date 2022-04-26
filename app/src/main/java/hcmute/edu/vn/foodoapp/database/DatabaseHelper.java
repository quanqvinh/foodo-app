package hcmute.edu.vn.foodoapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import hcmute.edu.vn.foodoapp.MyApplication;
import hcmute.edu.vn.foodoapp.activity.LoginActivity;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DB_PATH = "/data/data/hcmute.edu.vn.foodoapp/databases/";
    private static final String DB_NAME = "foodo.db";
    private static final int DB_VERSION = 1;
    private static DatabaseHelper instance = null;
    private static final Context context = MyApplication.getContext();

    public synchronized static DatabaseHelper getInstance() {
        if (instance == null){
            instance = new DatabaseHelper(context);
        }
        return instance;
    }

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists users(" +
                "id integer primary key autoincrement," +
                "username varchar(30)," +
                "password varchar(30)," +
                "address varchar(100)," +
                "phone varchar(10));");
        db.execSQL("create table if not exists foods (" +
                "id integer primary key autoincrement, " +
                "image integer, " +
                "name varchar(200), " +
                "type varchar(50), " +
                "description varchar(200), " +
                "price integer, " +
                "storeId integer);");
        db.execSQL("create table if not exists bills (" +
                "id integer primary key autoincrement, " +
                "userId integer, " +
                "createdAt varchar(200), " +
                "storeId integer, " +
                "totalPrice integer," +
                "address varchar(200));");
        db.execSQL("create table if not exists bill_details (" +
                "id integer primary key autoincrement, " +
                "foodId integer, " +
                "billId integer, " +
                "amount integer, " +
                "price integer);");
        db.execSQL("create table if not exists stores (" +
                "id integer primary key autoincrement, " +
                "image integer, " +
                "name varchar(100), " +
                "openAt varchar(100), " +
                "closeAt varchar(100)," +
                "address varchar(200));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
