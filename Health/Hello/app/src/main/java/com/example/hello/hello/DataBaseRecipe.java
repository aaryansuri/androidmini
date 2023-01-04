package com.example.hello.hello;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hello.hello.RecipeContract.RecipeEntry;
import com.example.hello.hello.RecipeContract.UserDetailsEntry;
public class DataBaseRecipe extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "app.db";

    public static final int DATABASE_VERSION = 2;

    public DataBaseRecipe(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {


        String SQL_CREATE_RECIPES_TABLE = "CREATE TABLE " + RecipeEntry.TABLE_NAME
                + "("
                + RecipeEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + RecipeEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + RecipeEntry.COLUMN_INGREDIENT + " TEXT, "
                + RecipeEntry.COLUMN_CALORIES + " INTEGER, "
                + RecipeEntry.COLUMN_TIME + " INTEGER, "
                + RecipeEntry.COLUMN_IMAGE_PATH + " TEXT, "
                + RecipeEntry.COLUMN_STEP + " TEXT); ";

        db.execSQL(SQL_CREATE_RECIPES_TABLE);

        String SQL_CREATE_USER_TABLE = "CREATE TABLE " + UserDetailsEntry.TABLE_NAME
                + "("
                + UserDetailsEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + UserDetailsEntry.COLUMN_EMAIL + " TEXT NOT NULL, "
                + UserDetailsEntry.COLUMN_GENDER + " TEXT, "
                + UserDetailsEntry.COLUMN_AGE + " TEXT, "
                + UserDetailsEntry.COLUMN_HEIGHT + " TEXT, "
                + UserDetailsEntry.COLUMN_WEIGHT + " TEXT, "
                + UserDetailsEntry.COLUMN_BMI + " TEXT, "
                + UserDetailsEntry.COLUMN_GOAL + " TEXT, "
                + UserDetailsEntry.COLUMN_GOAL_WEIGHT + " TEXT, "
                + UserDetailsEntry.COLUMN_WEEKLY_GOAL + " TEXT, "
                + UserDetailsEntry.COLUMN_ACTIVITY_LEVEL + " TEXT, "
                + UserDetailsEntry.COLUMN_DAILY_CALORIES + " TEXT); ";

        db.execSQL(SQL_CREATE_USER_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}