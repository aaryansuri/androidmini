package com.example.hello.hello.LoginSignup;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.hello.hello.AddRecipe.AddIngredients;
import com.example.hello.hello.AddRecipe.AddSteps;
import com.example.hello.hello.AddRecipe.RecipeNameImage;
import com.example.hello.hello.R;
import com.example.hello.hello.ViewPagerAdapter;

public class RegisterActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        toolbar = findViewById(R.id.toolbar_id);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.view_pager_id);
        tabLayout = findViewById(R.id.tab_layout_id);


        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(new Goal(),"Goal");
        viewPagerAdapter.addFragment(new ActivityLevel(),"Activity Level");
        viewPagerAdapter.addFragment(new You(),"You");
        viewPagerAdapter.addFragment(new WeeklyGoal(),"Weekly Goal");
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
