package com.example.hello.hello;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hello.hello.AddRecipe.AddRecipe;
import com.example.hello.hello.Counter.CounterActivity;
import com.example.hello.hello.LoginSignup.DailyCalorie;
import com.example.hello.hello.LoginSignup.RegisterActivity;
import com.example.hello.hello.LoginSignup.UserDetails;
import com.example.hello.hello.Navigation.MyFavouriteRecipieActivity;
import com.example.hello.hello.Navigation.MyRecipiesActivity;
import com.example.hello.hello.Navigation.UserDetailsActivity;
import com.example.hello.hello.SearchRecipe.SearchRecipeActivity;
import com.google.firebase.auth.FirebaseAuth;


public class dashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    CardView recipe_search;
    CardView pedometer;
    CardView myrecipes;
    CardView map;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FirebaseAuth firebaseAuth;
    TextView email;
    ImageView user_details;
    DataBaseRecipe mDbHelper;


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        System.exit(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);

        mDbHelper =  new DataBaseRecipe(this);
        toolbar = findViewById(R.id.toolbar_id);
        setSupportActionBar(toolbar);
        firebaseAuth = FirebaseAuth.getInstance();
        drawerLayout=findViewById(R.id.Drawer_layout_id);
        navigationView=findViewById(R.id.Navigation_view_id);
        map = findViewById(R.id.map_id);

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboardActivity.this,MapActivity.class));
            }
        });
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle =new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer);
        drawerLayout.setDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.nice_blue));
        toggle.syncState();

        View header = navigationView.getHeaderView(0);
        email = header.findViewById(R.id.user_email_id);
        user_details = header.findViewById(R.id.user_image_details_id);
        String query = "SELECT email from UserDetails;";

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor cursor= db.rawQuery(query,null);
        String mail="";
        int col = cursor.getColumnIndex("email");
        if(cursor.moveToFirst()){
            mail = cursor.getString(col);
        }

        email.setText(mail);

        user_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboardActivity.this,UserDetailsActivity.class));
            }
        });

        recipe_search = findViewById(R.id.recipe_search_id);
        pedometer = findViewById(R.id.pedometer_id);
        myrecipes = findViewById(R.id.my_recipes_id);

        recipe_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboardActivity.this,SearchRecipeActivity.class));
            }
        });

        pedometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboardActivity.this,CounterActivity.class));
            }
        });
        myrecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboardActivity.this,AddRecipe.class));

            }
        });

        cursor.close();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.profile_icon_id){
            startActivity(new Intent(dashboardActivity.this,DailyCalorie.class));
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch(id){
            case R.id.my_recipes_id:
                startActivity(new Intent(dashboardActivity.this,MyRecipiesActivity.class));

                break;

            case R.id.my_Logout_id:
            {
                firebaseAuth.signOut();
                startActivity(new Intent(dashboardActivity.this,WelcomeActivity.class));
            }
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;

    }
}
