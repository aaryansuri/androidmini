package com.example.hello.hello.LoginSignup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hello.hello.R;

public class DailyCalorie extends AppCompatActivity {
    Button submit;
    EditText weight,bodyfat;
    CheckBox male,female;
    static String daily_calorie;
    int kg,fat;
    double ht;
    TextView daily_cal;
    TextView bmi;
    EditText height;

    public static String getDaily_calorie() {
        return daily_calorie;
    }

    public static void setDaily_calorie(String daily_calorie) {
        DailyCalorie.daily_calorie = daily_calorie;
    }

    double leanfactormale,leanfactorfemale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_calorie);
        weight = findViewById(R.id.weight_id);
        bodyfat = findViewById(R.id.fatper_id);
        male = findViewById(R.id.checkbox_male_id);
        female = findViewById(R.id.checkbox_female_id);
        submit = findViewById(R.id.submit_id);
        daily_cal = findViewById(R.id.calorie_need_id);
        bmi = findViewById(R.id.bmi_index_id);
        height = findViewById(R.id.height_id);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kg = Integer.parseInt(weight.getText().toString());
                fat = Integer.parseInt(bodyfat.getText().toString());
                ht = Float.parseFloat(height.getText().toString());

                double BMI = (kg/(ht*ht));

                bmi.setText("Your BMI is "+Double.toString(BMI).substring(0,5));

                UserDetails.setBMI("Your BMI is "+Double.toString(BMI).substring(0,5));

                if(male.isChecked() && !female.isChecked()){

                    int bodyfatpercentage = Integer.parseInt(bodyfat.getText().toString());
                    if(bodyfatpercentage>= 10 && bodyfatpercentage<=14){
                        leanfactormale = 1;
                    }
                    if(bodyfatpercentage>= 15 && bodyfatpercentage<=20){
                        leanfactormale = 0.95;
                    }
                    if(bodyfatpercentage>= 21 && bodyfatpercentage<=28){
                        leanfactormale = 0.90;
                    }
                    if(bodyfatpercentage>28){
                        leanfactormale = 0.85;
                    }
                    double calorie_need_male = kg*1.0*leanfactormale*24;
                    daily_calorie = Double.toString(calorie_need_male);
                    setDaily_calorie(daily_calorie);
                }



                if(female.isChecked() && !male.isChecked()){

                    int bodyfatpercentage = Integer.parseInt(bodyfat.getText().toString());
                    if(bodyfatpercentage>= 14 && bodyfatpercentage<=18){
                        leanfactorfemale = 1;
                    }
                    if(bodyfatpercentage>= 19 && bodyfatpercentage<=28){
                        leanfactorfemale = 0.95;
                    }
                    if(bodyfatpercentage>= 29 && bodyfatpercentage<=38){
                        leanfactorfemale = 0.90;
                    }
                    if(bodyfatpercentage>38){
                        leanfactorfemale = 0.85;
                    }
                    double calorie_need_female = kg*leanfactorfemale*24*0.9;
                    daily_calorie = Double.toString(calorie_need_female);
                    setDaily_calorie(daily_calorie);
                }



                if (male.isChecked() && female.isChecked()){
                    Toast.makeText(getApplicationContext(),"Please check only one",Toast.LENGTH_LONG).show();
                }
                String mytext = "Your Daily calorie requirement is "+getDaily_calorie();
                UserDetails.setDailyCalReq(mytext);
                daily_cal.setText(mytext);
            }

        });



    }
}
