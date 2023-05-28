package com.example.languagecenter.LanguageCenter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.languagecenter.Language_Center;
import com.example.languagecenter.R;
import com.example.languagecenter.Students;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LanguageCenterActivity extends AppCompatActivity {
    private String KEY_ACCOUNT = "KEY_ACCOUNT_LANGUAGE";
    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;
    static Language_Center account_language_center;

    public Language_Center getAccount_language_center() {
        return account_language_center;
    }

    public void setAccount_language_center(Language_Center account_language_center) {
        this.account_language_center = account_language_center;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_center);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        setAccount_language_center((Language_Center) bundle.getSerializable(KEY_ACCOUNT));
        Log.d("xyz", account_language_center.toString());

        viewPager = findViewById(R.id.lang_viewpager);
        bottomNavigationView = findViewById(R.id.lang_bottom);

        LangCenterViewPagerAdapter adapter = new LangCenterViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.lang_home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.lang_post).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.lang_noti).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.lang_profile).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.lang_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.lang_post:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.lang_noti:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.lang_profile:
                        viewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
    }
}