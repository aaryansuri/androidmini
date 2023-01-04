package com.example.hello.hello.LoginSignup;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.hello.hello.DataBaseRecipe;
import com.example.hello.hello.RecipeContract;
import com.example.hello.hello.dashboardActivity;
import com.example.hello.hello.R;

import static com.example.hello.hello.LoginSignup.UserDetails.getActivityLevel;
import static com.example.hello.hello.LoginSignup.UserDetails.getAge;
import static com.example.hello.hello.LoginSignup.UserDetails.getCurrentWeight;
import static com.example.hello.hello.LoginSignup.UserDetails.getEmailId;
import static com.example.hello.hello.LoginSignup.UserDetails.getGENDER;
import static com.example.hello.hello.LoginSignup.UserDetails.getGOAL;
import static com.example.hello.hello.LoginSignup.UserDetails.getGoalWeight;
import static com.example.hello.hello.LoginSignup.UserDetails.getHEIGHT;
import static com.example.hello.hello.LoginSignup.UserDetails.getWeeklyGoal;

public class WeeklyGoal extends Fragment {

    RadioGroup radioGroup;
    static EditText goal_weight;
    Button submit;
    static DataBaseRecipe mDbHelper;

    public WeeklyGoal() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weekly_goal_fragment, container, false);
        radioGroup = view.findViewById(R.id.radio_group_id);
        goal_weight = view.findViewById(R.id.weight_id);
        submit = view.findViewById(R.id.submit_id);

        mDbHelper = new DataBaseRecipe(getActivity());

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checked = group.findViewById(checkedId);
                UserDetails.setWeeklyGoal(checked.getText().toString());
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Height = You.height.getText().toString();
                String Weight = You.weight.getText().toString();

                if (TextUtils.isEmpty(Height) && TextUtils.isEmpty(Weight)) {
                    Toast.makeText(getContext(), "Please Enter required info", Toast.LENGTH_LONG).show();
                } else {
                    UserDetails.setHEIGHT(Height);
                    UserDetails.setCurrentWeight(Weight);
                }

                String Goal_Weight = goal_weight.getText().toString();

                if (TextUtils.isEmpty(Goal_Weight)) {
                    Toast.makeText(getContext(), "Please Enter required info", Toast.LENGTH_LONG).show();
                } else {
                    UserDetails.setGoalWeight(goal_weight.getText().toString());
                }

                addToDatabaseUser();


                startActivity(new Intent(getActivity(), dashboardActivity.class));
            }
        });

        return view;
    }
        public static void addToDatabaseUser(){

            SQLiteDatabase db = mDbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(RecipeContract.UserDetailsEntry.COLUMN_EMAIL,getEmailId());
            values.put(RecipeContract.UserDetailsEntry.COLUMN_GENDER,getGENDER());
            values.put(RecipeContract.UserDetailsEntry.COLUMN_AGE,getAge());
            values.put(RecipeContract.UserDetailsEntry.COLUMN_GOAL,getGOAL());
            values.put(RecipeContract.UserDetailsEntry.COLUMN_GOAL_WEIGHT,getGoalWeight());
            values.put(RecipeContract.UserDetailsEntry.COLUMN_HEIGHT,getHEIGHT());
            values.put(RecipeContract.UserDetailsEntry.COLUMN_WEIGHT,getCurrentWeight());
            values.put(RecipeContract.UserDetailsEntry.COLUMN_ACTIVITY_LEVEL,getActivityLevel());
            values.put(RecipeContract.UserDetailsEntry.COLUMN_WEEKLY_GOAL,getWeeklyGoal());
            //values.put(RecipeContract.UserDetailsEntry.COLUMN_BMI,getBMI());
            //values.put(RecipeContract.UserDetailsEntry.COLUMN_DAILY_CALORIES,getDailyCalReq());
            Log.i("hello",getWeeklyGoal());

            db.insert(RecipeContract.UserDetailsEntry.TABLE_NAME,null,values);




        }


    }



