package com.example.hello.hello.Navigation;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hello.hello.DataBaseRecipe;
import com.example.hello.hello.R;
import com.example.hello.hello.RecipeContract;

import java.io.File;

public class DisplayUserRecipe extends AppCompatActivity {

    ImageView recipe_image;
    int recipe_id;
    TextView recipe_name,time,calories,ingredients,steps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user_recipe);

        Intent intent = getIntent();
        recipe_id = intent.getIntExtra("RECIPE_ID",0)+1;

        recipe_image = findViewById(R.id.my_recipe_image_id);
        recipe_name = findViewById(R.id.recipe_name_id);
        time = findViewById(R.id.recipe_time);
        calories = findViewById(R.id.my_calories_id);
        ingredients = findViewById(R.id.ingredients_list_id);
        steps = findViewById(R.id.steps_list_id);



        DataBaseRecipe dataBaseRecipe = new DataBaseRecipe(this);
        SQLiteDatabase sqLiteDatabase = dataBaseRecipe.getReadableDatabase();

        String query = "SELECT * FROM Recipes where " + RecipeContract.RecipeEntry.COLUMN_ID+" = "+recipe_id+";";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        try {

            cursor.moveToFirst();
            int nameColumnIndex = cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_NAME);
            int timeColumnIndex = cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_TIME);
            int calorieColumnIndex = cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_CALORIES);
            int IngColumnIndex = cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_INGREDIENT);
            int StepColumnIndex = cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_STEP);
            int ImageColumnIndex = cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_IMAGE_PATH);

        recipe_name.append(cursor.getString(nameColumnIndex));
        time.append(Integer.toString(cursor.getInt(timeColumnIndex)));
        calories.append(Integer.toString(cursor.getInt(calorieColumnIndex)));

        String[] splitIng = cursor.getString(IngColumnIndex).split("#");
        ingredients.setText("");
        for(int i =0 ;i<splitIng.length;i++){
            ingredients.append(splitIng[i]);
            ingredients.append("\n");
        }

        String[] splitStep = cursor.getString(StepColumnIndex).split("#");
            for(int i = 0 ;i<splitStep.length;i++){
                steps.append((i+1)+ "." +splitStep[i]);
                steps.append("\n");
            }

            File image_file = new File(cursor.getString(ImageColumnIndex));
            Log.i("image",cursor.getString(ImageColumnIndex));
            if(image_file.exists()){
                Bitmap bitmap = BitmapFactory.decodeFile(image_file.getAbsolutePath());
                recipe_image.setImageBitmap(bitmap);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
