package hcmute.edu.vn.foodoapp.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import hcmute.edu.vn.foodoapp.R;
import hcmute.edu.vn.foodoapp.adapter.FoodAdapter;
import hcmute.edu.vn.foodoapp.adapter.MainViewPagerAdapter;
import hcmute.edu.vn.foodoapp.adapter.StoreAdapter;
import hcmute.edu.vn.foodoapp.model.Food;
import hcmute.edu.vn.foodoapp.model.Store;
import hcmute.edu.vn.foodoapp.service.BillService;
import hcmute.edu.vn.foodoapp.service.FoodService;
import hcmute.edu.vn.foodoapp.service.StoreService;
import hcmute.edu.vn.foodoapp.service.UserService;

public class MainActivity extends AppCompatActivity {
    final public static BillService billService = new BillService();
    final public static FoodService foodService = new FoodService();
    final public static StoreService storeService = new StoreService();
    final public static UserService userService = new UserService();
    public static int userId;



    ViewPager vpMain;
    TabLayout tlNavigation;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        userId = getIntent().getIntExtra(LoginActivity.USERID_AUTHENTICATED_MESSAGE, 2);

        vpMain = findViewById(R.id.vpMain);
        tlNavigation = findViewById(R.id.tlNavigation);

        vpMain.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));
        tlNavigation.setupWithViewPager(vpMain);
    }
}