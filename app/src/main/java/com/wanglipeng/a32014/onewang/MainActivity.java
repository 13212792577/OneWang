package com.wanglipeng.a32014.onewang;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.wanglipeng.a32014.onewang.fragment.HomeFragment;
import com.wanglipeng.a32014.onewang.fragment.MovieFragment;
import com.wanglipeng.a32014.onewang.fragment.MusicFragment;
import com.wanglipeng.a32014.onewang.fragment.ReadingFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    FragmentManager fragmentManager;
    HomeFragment homeFragment ;
    ReadingFragment readingFragment ;
    MusicFragment musicFragment ;
    MovieFragment movieFragment ;

    RadioGroup radioGroup;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup = (RadioGroup) findViewById(R.id.rg_bottom);
        textView = (TextView) findViewById(R.id.text_center);
        initFragment();
        fragmentManager = getSupportFragmentManager();
        initSetManager();

        radioGroup.setOnCheckedChangeListener(this);
    }

    private void initSetManager() {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.container,homeFragment);
        ft.add(R.id.container,readingFragment);
        ft.add(R.id.container,musicFragment);
        ft.add(R.id.container,movieFragment);
        ft.show(homeFragment);
        ft.hide(readingFragment);
        ft.hide(musicFragment);
        ft.hide(movieFragment);
        ft.commit();
    }

    private void initFragment() {
        homeFragment = new HomeFragment();
        readingFragment = new ReadingFragment();
        musicFragment = new MusicFragment();
        movieFragment = new MovieFragment();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        hideAllfragment();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        switch (checkedId){
            case R.id.one_button:
                textView.setBackgroundResource(R.drawable.nav_title);
                textView.setText("");
                textView.setTextSize(15);
                ft.show(homeFragment);
                break;
            case R.id.two_button:
                textView.setBackgroundResource(android.R.color.transparent);
                textView.setText("阅读");
                textView.setTextSize(20);
                ft.show(readingFragment);
                break;
            case R.id.three_button:
                textView.setBackgroundResource(android.R.color.transparent);
                textView.setText("音乐");
                textView.setTextSize(20);
                ft.show(musicFragment);
                break;
            case R.id.four_button:
                textView.setBackgroundResource(android.R.color.transparent);
                textView.setText("电影");
                textView.setTextSize(20);
                ft.show(movieFragment);
                break;
        }
        ft.commit();

    }

    private void hideAllfragment() {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.hide(homeFragment);
        ft.hide(readingFragment);
        ft.hide(musicFragment);
        ft.hide(movieFragment);
        ft.commit();
    }
}
