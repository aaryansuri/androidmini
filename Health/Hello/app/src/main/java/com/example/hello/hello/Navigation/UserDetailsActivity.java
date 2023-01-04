package com.example.hello.hello.Navigation;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hello.hello.DataBaseRecipe;
import com.example.hello.hello.LoginSignup.RegisterActivity;
import com.example.hello.hello.LoginSignup.UserDetails;
import com.example.hello.hello.R;
import com.example.hello.hello.RecipeContract;



public class UserDetailsActivity extends AppCompatActivity {

    TextView goal,activity_level,gender,height,current_weight,goal_weight,weekly_goal,age;
    Button edit_button;

    static DataBaseRecipe mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        edit_button = findViewById(R.id.edit_id);

        goal = findViewById(R.id.my_goal_id);
        activity_level = findViewById(R.id.activity_level_id);
        gender = findViewById(R.id.gender_id);
        height = findViewById(R.id.height_id);
        current_weight = findViewById(R.id.weight_id);
        goal_weight = findViewById(R.id.goal_weight_id);
        weekly_goal = findViewById(R.id.weekly_goal_id);
        age = findViewById(R.id.birth_date_id);

        mDbHelper = new DataBaseRecipe(this);

        findUserDetails();




        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserDetailsActivity.this,RegisterActivity.class));
            }
        });

    }

    public void findUserDetails() {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();




        String[] projection = {
                RecipeContract.UserDetailsEntry.COLUMN_EMAIL,
                RecipeContract.UserDetailsEntry.COLUMN_AGE,
                RecipeContract.UserDetailsEntry.COLUMN_GENDER,
                RecipeContract.UserDetailsEntry.COLUMN_HEIGHT,
                RecipeContract.UserDetailsEntry.COLUMN_WEIGHT,
                RecipeContract.UserDetailsEntry.COLUMN_GOAL,
                RecipeContract.UserDetailsEntry.COLUMN_GOAL_WEIGHT,
                RecipeContract.UserDetailsEntry.COLUMN_WEEKLY_GOAL,
                RecipeContract.UserDetailsEntry.COLUMN_ACTIVITY_LEVEL};

        String query = "SELECT * FROM UserDetails;";

        //Cursor cursor = db.query(RecipeContract.UserDetailsEntry.TABLE_NAME, projection,null,null,null,null,null,null);
        Cursor cursor = db.rawQuery(query,null);

        if(cursor==null) Log.i("MSG:","null c");
        try {
            cursor.moveToFirst();

            int ageColumnIndex = cursor.getColumnIndex(RecipeContract.UserDetailsEntry.COLUMN_AGE);
            int genderColumnIndex = cursor.getColumnIndex(RecipeContract.UserDetailsEntry.COLUMN_GENDER);
            int heightColumnIndex = cursor.getColumnIndex(RecipeContract.UserDetailsEntry.COLUMN_HEIGHT);
            int weightColumnIndex = cursor.getColumnIndex(RecipeContract.UserDetailsEntry.COLUMN_WEIGHT);
            int goalColumnIndex = cursor.getColumnIndex(RecipeContract.UserDetailsEntry.COLUMN_GOAL);
            int goalWeightColumnIndex = cursor.getColumnIndex(RecipeContract.UserDetailsEntry.COLUMN_GOAL_WEIGHT);
            int weeklyGoalColumnIndex = cursor.getColumnIndex(RecipeContract.UserDetailsEntry.COLUMN_WEEKLY_GOAL);
            int activityLevelColumnIndex = cursor.getColumnIndex(RecipeContract.UserDetailsEntry.COLUMN_ACTIVITY_LEVEL);





                goal.append(cursor.getString(goalColumnIndex));
                activity_level.append(cursor.getString(activityLevelColumnIndex));
                gender.append(cursor.getString(genderColumnIndex));
                height.append(cursor.getString(heightColumnIndex));
                current_weight.append(cursor.getString(weightColumnIndex));
                goal_weight.append(cursor.getString(goalWeightColumnIndex));
                weekly_goal.append(cursor.getString(weeklyGoalColumnIndex));
                age.append(cursor.getString(ageColumnIndex));



        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }

    }






    }



