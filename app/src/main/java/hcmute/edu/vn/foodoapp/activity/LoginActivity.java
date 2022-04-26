package hcmute.edu.vn.foodoapp.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.List;

import hcmute.edu.vn.foodoapp.R;
import hcmute.edu.vn.foodoapp.database.DatabaseHelper;
import hcmute.edu.vn.foodoapp.model.Bill;
import hcmute.edu.vn.foodoapp.model.BillDetails;
import hcmute.edu.vn.foodoapp.model.Food;
import hcmute.edu.vn.foodoapp.model.Store;
import hcmute.edu.vn.foodoapp.model.User;
import hcmute.edu.vn.foodoapp.service.BillService;
import hcmute.edu.vn.foodoapp.service.FoodService;
import hcmute.edu.vn.foodoapp.service.StoreService;
import hcmute.edu.vn.foodoapp.service.UserService;

public class LoginActivity extends AppCompatActivity {
    final public static String USERID_AUTHENTICATED_MESSAGE = "a Vinh cho em xin gui UserId";

    MaterialButton btnLogin;
    UserService userService;
    EditText etUsername, etPassword;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this::loginHandler);

//        createData();

//        SQLiteDatabase dbRead = DatabaseHelper.getInstance().getReadableDatabase();
//        Cursor cursor = dbRead.rawQuery("select b.id, bd.id from bills b join bill_details bd on b.id = bd.billId " +
//                "where b.id = 1;", null);
//        while (cursor.moveToNext()){
//            Log.d("ABCXYZ", cursor.getInt(0) + " " + cursor.getInt(1));
//        }
//        cursor.close();
    }

    private void loginHandler(View view) {
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        Log.d("ACC", username + " " + password);
        if (username.equals("") || password.equals("")){
            Toast.makeText(this.getApplicationContext(), "Vui lòng điền đầy đủ thông tin!"
                    , Toast.LENGTH_SHORT).show();
            return;
        }
        userService = new UserService();
        int userId = userService.authenticate(username, password);
        if (userId > -1){
            Intent intent = new Intent(this.getApplicationContext(), MainActivity.class);
            intent.putExtra(USERID_AUTHENTICATED_MESSAGE, userId);
            startActivity(intent);
        }
        else {
            Toast.makeText(this.getApplicationContext(), "Tài khoản hoặc mật khẩu không đúng!"
                    , Toast.LENGTH_SHORT).show();
            return;
        }
    }

//    @RequiresApi(api = Build.VERSION_CODES.R)
//    private void createData(){
//        SQLiteDatabase db = DatabaseHelper.getInstance().getWritableDatabase();
//
////         User
//        UserService userService = new UserService();
//        User user = new User(null, "luuAG", "12345678", "Ở đâu đó", "0823832343");
//        userService.insert(user);
//
////         Store
//        StoreService storeService = new StoreService();
//        Store kfc = new Store(null, R.drawable.store1, "KFC", "7:00AM", "22:00PM","Ngon hơn người yêu cũ của bạn");
//        Store domino = new Store(null, R.drawable.store2, "Domino", "7:00AM", "22:00PM","Ăn là ghiền");
//        storeService.insert(kfc);
//        storeService.insert(domino);
//
////         Food cho KFC
//        FoodService foodService = new FoodService();
//        Food f1 = new Food(null, R.drawable.food1, "Gà rán 1", "Đồ ăn nhanh", "1 đùi hoặc miếng gà rán", 32000, 1);
//        Food f2 = new Food(null, R.drawable.food2, "Combo 1", "Đồ ăn nhanh", "1 đùi gà, 1 khoai tây chiên, 1 nước", 45000, 1);
//        foodService.insert(f1);
//        foodService.insert(f2);
//
////         Bill
//        BillService billService = new BillService();
//
//        Bill bill = new Bill(null, 1, "4:12PM 4/24/2022", 1, null);
//
//        BillDetails d1 = new BillDetails(null, 1, null, 1, 0);
//        d1.setFood(foodService.getOne(1));
//        d1.setPrice(billService.calculateBillDetailsPrice(d1));
//        BillDetails d2 = new BillDetails(null, 2, null, 1, 0);
//        d2.setFood(foodService.getOne(2));
//        d2.setPrice(billService.calculateBillDetailsPrice(d2));
//
//        bill.setDetails(List.of(d1, d2));
//
//        bill.setTotalPrice(billService.calculateBillTotalPrice(bill));
//        billService.insertBillWithDetails(bill);
//        db.close();
//    }
}