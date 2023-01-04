package com.example.hello.hello;

import android.provider.BaseColumns;

public final class RecipeContract {

    RecipeContract() {}

    public static final class RecipeEntry implements BaseColumns {

        public static final String TABLE_NAME = "Recipes";
        public static final String COLUMN_ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_INGREDIENT = "ingredients";
        public static final String COLUMN_CALORIES = "calories";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_STEP = "step";
        public static final String COLUMN_IMAGE_PATH = "image_path";

    }

    public static final class UserDetailsEntry implements BaseColumns {

        public static final String TABLE_NAME = "UserDetails";
        public static final String COLUMN_ID = BaseColumns._ID;
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_AGE = "age";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_WEIGHT = "weight";
        public static final String COLUMN_HEIGHT = "height";
        public static final String COLUMN_BMI = "bmi";
        public static final String COLUMN_DAILY_CALORIES = "daily_calories";
        public static final String COLUMN_GOAL = "goal";
        public static final String COLUMN_ACTIVITY_LEVEL = "activity_level";
        public static final String COLUMN_GOAL_WEIGHT = "goal_weight";
        public static final String COLUMN_WEEKLY_GOAL= "weekly_goal";

    }
}
