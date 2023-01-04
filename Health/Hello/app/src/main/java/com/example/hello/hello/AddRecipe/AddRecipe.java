package com.example.hello.hello.AddRecipe;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.hello.hello.R;
import com.example.hello.hello.ViewPagerAdapter;

public class AddRecipe extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_add);
        toolbar = findViewById(R.id.toolbar_id);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.view_pager_id);
        tabLayout = findViewById(R.id.tab_layout_id);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(new AddIngredients(),"Ingredients");
        viewPagerAdapter.addFragment(new AddSteps(),"Steps");
        viewPagerAdapter.addFragment(new RecipeNameImage(),"Finally");
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_subject_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_whatshot_black_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_sentiment_satisfied_black_24dp);

    }
}
