package hcmute.edu.vn.foodoapp.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
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
//        testSearch();

//        SQLiteDatabase dbRead = DatabaseHelper.getInstance().getReadableDatabase();
//        Cursor cursor = dbRead.rawQuery("select b.id, bd.id from bills b join bill_details bd on b.id = bd.billId " +
//                "where b.id = 1;", null);
//        while (cursor.moveToNext()){
//            Log.d("ABCXYZ", cursor.getInt(0) + " " + cursor.getInt(1));
//        }
//        cursor.close();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            return false;
        }
        return super.onKeyDown(keyCode, event);
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

    @RequiresApi(api = Build.VERSION_CODES.R)
    private void createData(){
        SQLiteDatabase db = DatabaseHelper.getInstance().getWritableDatabase();

//         User
        UserService userService = new UserService();
        User user1 = new User(1, "luuAG", "12345678", "Ở đâu đó", "0823832343");
        User user2 = new User(2, "quanqvinh", "12345678", "1135 Huỳnh Tấn Phát, Phú Thuận, Quận 7, HCM", "0823832323");
        userService.insert(user1);
        userService.insert(user2);

//         Store
        StoreService storeService = new StoreService();
        Store kfc = new Store(1, R.drawable.kfc, "Gà Rán KFC", "7:00AM", "22:00PM","Ngon hơn người yêu cũ của bạn");
        Store domino = new Store(2, R.drawable.domino, "Pizza Domino", "7:30AM", "21:00PM","Ăn là ghiền");
        Store tch = new Store(3, R.drawable.the_coffee_house, "The Coffee House", "7:30AM", "21:30PM","Cafe nguyên chất");
        Store highland = new Store(4, R.drawable.highland_coffee, "Highland Coffee", "7:30AM", "21:30PM","Cafe nguyên chất");
        storeService.insert(kfc);
        storeService.insert(domino);
        storeService.insert(tch);
        storeService.insert(highland);

//         Food cho KFC
        FoodService foodService = new FoodService();
        foodService.insert(new Food(1, R.drawable.kfc_food1, "Gà rán (1 miếng)", null, "1 Miếng Gà Giòn Cay / 1 Miếng Gà Giòn Không Cay / 1 Miếng Gà Truyền", 35000, kfc.getId()));
        foodService.insert(new Food(2, R.drawable.kfc_food2, "Gà rán (2 miếng)", null, "2 Miếng Gà Giòn Cay / 2 Miếng Gà Giòn Không Cay / 2 Miếng Gà Truyền", 68000, kfc.getId()));
        foodService.insert(new Food(3, R.drawable.kfc_food3, "Gà rán (3 miếng)", null, "3 Miếng Gà Giòn Cay / 3 Miếng Gà Giòn Không Cay / 3 Miếng Gà Truyền", 68000, kfc.getId()));
        foodService.insert(new Food(4, R.drawable.kfc_food4, "Gà rán (6 miếng)", null, "6 Miếng Gà Giòn Cay / 6 Miếng Gà Giòn Không Cay / 6 Miếng Gà Truyền", 68000, kfc.getId()));
        foodService.insert(new Food(5, R.drawable.kfc_food5, "Combo Gà Rán", null, "2 Miếng Gà + 1 Pepsi", 78000, kfc.getId()));
        foodService.insert(new Food(6, R.drawable.kfc_food6, "Combo Mì Ý", null, "1 Mì Ý Xốt Cà Gà Viên + 1 Miếng Gà / 2 Xiên Gà Tenderods + 1 Pepsi Lon", 83000, kfc.getId()));

//         Food cho Domino
        foodService.insert(new Food(7, R.drawable.domino_food1, "Pizza Bò & Tôm Nướng Kiểu Mỹ - Surf & Turf", null, "Xốt Cà Chua, Xốt Phô Mai, Phô Mai Mozzarella, Tôm, Thịt Bò Mexico, Cà Chua, Hành Tây", 99000, domino.getId()));
        foodService.insert(new Food(8, R.drawable.domino_food2, "Pizza 5 Loại Thịt Thượng Hạng - Meat Lovers", null, "Xốt Cà Chua, Phô Mai Mozzarella, Xúc Xích Pepperoni, Thịt Dăm Bông, Xúc Xich Ý, Thịt Bò Viên, Thịt Heo Muối", 99000, domino.getId()));
        foodService.insert(new Food(9, R.drawable.domino_food3, "Pizza Trứng Cút Xốt Phô Mai - Kid Mania", null, "Xốt Phô Mai, Phô Mai Mozzarella, Thịt Heo Muối, Bắp, Trứng Cút", 79000, domino.getId()));
        foodService.insert(new Food(10, R.drawable.domino_food4, "Pizza Phô Mai Hảo Hạng - Cheese Mania", null, "Xốt Cà Chua, phô Mai Mozzarella", 79000, domino.getId()));
        foodService.insert(new Food(11, R.drawable.domino_food5, "Pizza Bò Mê-Hi-Cô Thượng Hạng - Prime Beef", null, "Xốt phô mai, Xốt cà chua, bò viên kiểu Ý, thịt bò Mexico, hành tây, cà chua, phô mai Mozzarella", 249000, domino.getId()));
        foodService.insert(new Food(12, R.drawable.domino_food6, "Pizza 4 Vị - Pizza Big 4", null, "Pizza là sự kết hợp của 4 loại pizza HAWAIIAN – OCEAN MANIA – TERIYAKI CHICKEN – SURF & TURF", 329000, domino.getId()));

//         Food cho The Coffee House
        foodService.insert(new Food(13, R.drawable.tch_drink1, "Bạc Xỉu", null, "Bạc sỉu chính là \"Ly sữa trắng kèm một chút cà phê\"", 29000, tch.getId()));
        foodService.insert(new Food(14, R.drawable.tch_drink2, "Latte Nóng", null, "Một sự kết hợp tinh tế giữa vị đắng cà phê Espresso nguyên chất hòa quyện cùng vị sữa nóng ngọt ngào, bên trên là một lớp kem mỏng nhẹ tạo nên một tách cà phê hoàn hảo về hương vị lẫn nhãn quan", 49000, tch.getId()));
        foodService.insert(new Food(15, R.drawable.tch_drink3, "Trà Đào Cam Xả", null, "Vị thanh ngọt của đào, vị chua dịu của Cam Vàng nguyên vỏ, vị chát của trà đen tươi được ủ mới mỗi 4 tiếng, cùng hương thơm nồng đặc trưng của sả chính là điểm sáng làm nên sức hấp dẫn của thức uống này.", 45000, tch.getId()));
        foodService.insert(new Food(16, R.drawable.tch_drink4, "Trà Đen Macchiato", null, "Trà đen được ủ mới mỗi ngày, giữ nguyên được vị chát mạnh mẽ đặc trưng của lá trà, phủ bên trên là lớp Macchiato \"homemade\" bồng bềnh quyến rũ vị phô mai mặn mặn mà béo béo.", 49000, tch.getId()));
        foodService.insert(new Food(17, R.drawable.tch_drink5, "Chocolate Đá Xay", null, "Sữa và kem tươi béo ngọt được “cá tính hoá” bởi vị chocolate/sô-cô-la đăng đắng. Dành cho các tín đồ hảo ngọt. Lựa chọn hàng đầu nếu bạn đang cần chút năng lượng tinh thần.", 58000, tch.getId()));
        foodService.insert(new Food(18, R.drawable.tch_drink6, "Hi-Tea Yuzu", null, "Không chỉ nổi bật với sắc đỏ đặc trưng từ trà hoa Hibiscus, Hi-Tea Yuzu còn gây ấn tượng với topping Yuzu (quýt Nhật) lạ miệng, kết hợp cùng trân châu trắng dai giòn sần sật, nhai vui vui.", 45000, tch.getId()));

//         Food cho Highland Coffee
        foodService.insert(new Food(19, R.drawable.highland_drink1, "Bạc Xỉu Đá", null, "Nếu Phin Sữa Đá dành cho các bạn đam mê vị đậm đà, thì Bạc Xỉu Đá là một sự lựa chọn nhẹ “đô\" cà phê nhưng vẫn thơm ngon, chất lừ không kém!", 29000, highland.getId()));
        foodService.insert(new Food(20, R.drawable.highland_drink2, "PhiDi Choco", null, "PhinDi Choco - Cà phê Phin thế hệ mới với chất Phin êm hơn, kết hợp cùng Choco ngọt tan mang đến hương vị mới lạ, không thể hấp dẫn hơn!", 39000, highland.getId()));
        foodService.insert(new Food(21, R.drawable.highland_drink3, "Caramel Macchiato", null, "Thỏa mãn cơn thèm ngọt! Ly cà phê Caramel Macchiato bắt đầu từ dòng sữa tươi và lớp bọt sữa béo ngậy, sau đó hòa quyện cùng cà phê espresso đậm đà và sốt caramel ngọt ngào. Thông qua bàn tay điêu luyện của các chuyên gia pha chế, mọi thứ hoàn toàn được nâng tầm thành nghệ thuật! Bạn có thể tùy thích lựa chọn uống nóng hoặc dùng chung với đá.", 59000, highland.getId()));
        foodService.insert(new Food(22, R.drawable.highland_drink4, "Freeze Trà Xanh", null, "Thức uống rất được ưa chuộng! Trà xanh thượng hạng từ cao nguyên Việt Nam, kết hợp cùng đá xay, thạch trà dai dai, thơm ngon và một lớp kem dày phủ lên trên vô cùng hấp dẫn. Freeze Trà Xanh thơm ngon, mát lạnh, chinh phục bất cứ ai!", 49000, highland.getId()));
        foodService.insert(new Food(23, R.drawable.highland_drink5, "Caramel Phin Freeze", null, "Thơm ngon khó cưỡng! Được kết hợp từ cà phê truyền thống chỉ có tại Highlands Coffee, cùng với caramel, thạch cà phê và đá xay mát lạnh. Trên cùng là lớp kem tươi thơm béo và caramel ngọt ngào. Món nước phù hợp trong những cuộc gặp gỡ bạn bè, bởi sự ngọt ngào thường mang mọi người xích lại gần nhau.", 49000, highland.getId()));
        foodService.insert(new Food(24, R.drawable.highland_drink6, "Trà Thạch Vải", null, "Một sự kết hợp thú vị giữa trà đen, những quả vải thơm ngon và thạch giòn khó cưỡng, mang đến thức uống tuyệt hảo!", 39000, highland.getId()));


//         Bill
        BillService billService = new BillService();

        List<BillDetails> l = new ArrayList<BillDetails>();
        l.add(new BillDetails(1, 1, 1, 1, 35000));
        l.add(new BillDetails(2, 6, 1, 1, 83000));
        Bill bill = new Bill(1, user1.getId(), "4:12PM 4/24/2022", kfc.getId(), 35000 + 83000 + 15000, user1.getAddress(), l);
        billService.insertBillWithDetails(bill);

        l = new ArrayList<BillDetails>();
        l.add(new BillDetails(3, 23, 2, 2, 98000));
        bill = new Bill(2, user1.getId(), "14:52PM 4/26/2022", highland.getId(), 98000 + 15000, user1.getAddress(), l);
        billService.insertBillWithDetails(bill);

        l = new ArrayList<BillDetails>();
        l.add(new BillDetails(4, 8, 3, 1, 99000));
        l.add(new BillDetails(5, 10, 3, 1, 79000));
        l.add(new BillDetails(6, 11, 3, 1, 249000));
        bill = new Bill(3, user1.getId(), "18:50PM 4/26/2022", kfc.getId(), 99000 + 79000 + 249000 + 15000, user1.getAddress(), l);
        billService.insertBillWithDetails(bill);

        l = new ArrayList<BillDetails>();
        l.add(new BillDetails(7, 15, 4, 2, 90000));
        bill = new Bill(4, user2.getId(), "12:41PM 4/24/2022", tch.getId(), 90000 + 15000, user2.getAddress(), l);
        billService.insertBillWithDetails(bill);

        l = new ArrayList<BillDetails>();
        l.add(new BillDetails(8, 16, 5, 1, 49000));
        bill = new Bill(5, user2.getId(), "15:10PM 4/27/2022", tch.getId(), 49000 + 15000, user2.getAddress(), l);
        billService.insertBillWithDetails(bill);

        l = new ArrayList<BillDetails>();
        l.add(new BillDetails(9, 18, 6, 1, 45000));
        l.add(new BillDetails(10, 13, 6, 1, 29000));
        l.add(new BillDetails(11, 14, 6, 1, 45000));
        bill = new Bill(6, user2.getId(), "15:30PM 4/27/2022", tch.getId(), 45000 + 45000 + 29000 + 15000, user2.getAddress(), l);
        billService.insertBillWithDetails(bill);

        l = new ArrayList<BillDetails>();
        l.add(new BillDetails(12, 5, 7, 1, 78000));
        bill = new Bill(7, user2.getId(), "19:01PM 4/27/2022", kfc.getId(), 78000 + 15000, user2.getAddress(), l);
        billService.insertBillWithDetails(bill);

        l = new ArrayList<BillDetails>();
        l.add(new BillDetails(12, 9, 8, 1, 79000));
        l.add(new BillDetails(13, 12, 8, 1, 329000));
        bill = new Bill(8, user2.getId(), "20:21PM 4/27/2022", domino.getId(), 79000 + 329000 + 15000, user2.getAddress(), l);
        billService.insertBillWithDetails(bill);

//        BillDetails d1 = new BillDetails(1, 1, 1, 1, 0);
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
        db.close();
    }

    private void testSearch(){
        FoodService foodService = new FoodService();
        List<Food> foods = foodService.getByKeyword("Combo");
        for (Food f:foods) {
            Log.d("ABCXYZ", f.getName());
        }
    }
}