package com.example.hello.hello;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hello.hello.SearchRecipe.FragmentNutrition;
import com.example.hello.hello.SearchRecipe.InfredientsTab;
import com.example.hello.hello.SearchRecipe.WebsiteRefered;

public class pagerAdapter extends FragmentPagerAdapter {

    int noofTabs;

    public pagerAdapter(android.support.v4.app.FragmentManager fm,int numberoftabs){
        super(fm);
        this.noofTabs=numberoftabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch(i)
        {
            case 0 :
                InfredientsTab infredientsTab = new InfredientsTab();
                return  infredientsTab;
            case 1 :
                FragmentNutrition fragmentNutrition = new FragmentNutrition();
                return  fragmentNutrition;
            case 2 :
                WebsiteRefered websiteRefered = new WebsiteRefered();
                return websiteRefered;
             default:
                 return null;

        }
    }

    @Override
    public int getCount() {
        return noofTabs;
    }
}
