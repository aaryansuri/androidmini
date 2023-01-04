package com.example.hello.hello.LoginSignup;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.hello.hello.RecipeContract.UserDetailsEntry;
import com.example.hello.hello.DataBaseRecipe;

public class UserDetails {

    static private String GOAL;
    static private String ACTIVITY_LEVEL;
    static private String GENDER;
    static private String AGE;
    static private String HEIGHT;
    static private String CURRENT_WEIGHT;
    static private String GOAL_WEIGHT;
    static private String WEEKLY_GOAL;
    static private String EMAIL_ID;
    static private String BMI;
    static private String DAILY_CAL_REQ;



    public static String getAGE() {
        return AGE;
    }

    public static String getBMI() {
        return BMI;
    }

    public static void setBMI(String BMI) {
        UserDetails.BMI = BMI;
    }

    public static String getDailyCalReq() {
        return DAILY_CAL_REQ;
    }

    public static void setDailyCalReq(String dailyCalReq) {
        DAILY_CAL_REQ = dailyCalReq;
    }

    public static String getEmailId() {
        return EMAIL_ID;
    }

    public static void setEmailId(String emailId) {
        EMAIL_ID = emailId;
    }

    public static String getGOAL() {
        return GOAL;
    }

    public static void setGOAL(String GOAL) {
        UserDetails.GOAL = GOAL;
    }

    public static String getActivityLevel() {
        return ACTIVITY_LEVEL;
    }

    public static void setActivityLevel(String activityLevel) {
        ACTIVITY_LEVEL = activityLevel;
    }

    public static String getGENDER() {
        return GENDER;
    }

    public static void setGENDER(String GENDER) {
        UserDetails.GENDER = GENDER;
    }

    public static String getAge() {
        return AGE;
    }

    public static void setAGE(String birthDate) {
        AGE = birthDate;
    }

    public static String getHEIGHT() {
        return HEIGHT;
    }

    public static void setHEIGHT(String HEIGHT) {
        UserDetails.HEIGHT = HEIGHT;
    }

    public static String getCurrentWeight() {
        return CURRENT_WEIGHT;
    }

    public static void setCurrentWeight(String currentWeight) {
        CURRENT_WEIGHT = currentWeight;
    }

    public static String getGoalWeight() {
        return GOAL_WEIGHT;
    }

    public static void setGoalWeight(String goalWeight) {
        GOAL_WEIGHT = goalWeight;
    }

    public static String getWeeklyGoal() {
        return WEEKLY_GOAL;
    }

    public static void setWeeklyGoal(String weeklyGoal) {
        WEEKLY_GOAL = weeklyGoal;
    }

 
}
