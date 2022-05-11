package hcmute.edu.vn.foodoapp.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    final public static String MESSAGE = "abc";

    final public static BillService billService = new BillService();
    final public static FoodService foodService = new FoodService();
    final public static StoreService storeService = new StoreService();
    final public static UserService userService = new UserService();
    public static int userId;

    EditText etSearchInput;
    LinearLayout searchBar;
    ViewPager vpMain;
    TabLayout tlNavigation;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

//        Log.d("Vinh", R.drawable.domino + "");

        userId = getIntent().getIntExtra(LoginActivity.USERID_AUTHENTICATED_MESSAGE, 2);

        etSearchInput = findViewById(R.id.etSearchInput);
        searchBar = findViewById(R.id.llSearchInput);
        vpMain = findViewById(R.id.vpMain);
        tlNavigation = findViewById(R.id.tlNavigation);

        vpMain.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));
        tlNavigation.setupWithViewPager(vpMain);

        vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 2)
                    searchBar.getLayoutParams().height = 0;
                else
                    searchBar.getLayoutParams().height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        etSearchInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                    intent.putExtra(MESSAGE, etSearchInput.getText().toString());
                    startActivity(intent);
                    etSearchInput.setText("");
                    return true;
                }
                return false;
            }
        });

    }
}