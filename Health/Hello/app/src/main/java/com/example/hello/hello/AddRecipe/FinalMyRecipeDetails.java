package com.example.hello.hello.AddRecipe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.example.hello.hello.dashboardActivity;
import com.example.hello.hello.ListAdapter;
import com.example.hello.hello.R;
import com.example.hello.hello.nutrientitem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FinalMyRecipeDetails extends AppCompatActivity {
    ListView listView;
    ListAdapter listAdapter;

    List<nutrientitem> nutrientitemList;
    TextView recipe_name;
    TextView calories;
    LinearLayout linearLayout;
    ImageView imageView;
    Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_my_recipe_details);
        recipe_name = findViewById(R.id.recipe_name_id);
        calories = findViewById(R.id.total_cal);
        linearLayout = findViewById(R.id.linear_layout_id);
        listView = findViewById(R.id.list_view_id);
        Intent intent = getIntent();
        calories.append(Integer.toString(RecipeNameImage.total_cal));
        nutrientitemList = new ArrayList<>();
        done = findViewById(R.id.done_id);
        imageView = findViewById(R.id.image_id);
        String picture_path = intent.getStringExtra("picture_path");

        File image_file = new File(picture_path);
        if(image_file.exists()){
            Bitmap bitmap = BitmapFactory.decodeFile(image_file.getAbsolutePath());
            imageView.setImageBitmap(bitmap);
        }

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FinalMyRecipeDetails.this,dashboardActivity.class));
            }
        });


        recipe_name.setText(intent.getStringExtra("name"));


        for (Map.Entry<String,String> entry : RecipeNameImage.cal_per.entrySet()) {
            String name = entry.getKey();
            String cal = entry.getValue();
            nutrientitemList.add(new nutrientitem(name,cal));
        }
        listAdapter = new ListAdapter(getApplicationContext(),nutrientitemList);
        listView.setAdapter(listAdapter);
        RecipeNameImage.cal_per.clear();

        RecipeNameImage.total_cal=0;

    }
/*
    private void displayDatabaseInfo() {

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        DataBaseRecipe mDbHelper = new DataBaseRecipe(this);

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        //displayView = findViewById(R.id.tv_display_info);

        // Perform this raw SQL query "SELECT * FROM pets"
        // to get a Cursor that contains all rows from the pets table.
        //Cursor cursor = db.rawQuery("SELECT * FROM " + RecipeContract.RecipeEntry.TABLE_NAME, null);

        String[] projection = {
                RecipeContract.RecipeEntry.COLUMN_ID,
                RecipeContract.RecipeEntry.COLUMN_NAME,
                RecipeContract.RecipeEntry.COLUMN_TIME,
                RecipeContract.RecipeEntry.COLUMN_CALORIES,
                RecipeContract.RecipeEntry.COLUMN_INGREDIENT,
                RecipeContract.RecipeEntry.COLUMN_STEP1,
                RecipeContract.RecipeEntry.COLUMN_STEP2,
                RecipeContract.RecipeEntry.COLUMN_STEP3,
                RecipeContract.RecipeEntry.COLUMN_STEP4,
                RecipeContract.RecipeEntry.COLUMN_STEP5,
                RecipeContract.RecipeEntry.COLUMN_STEP6,
                RecipeContract.RecipeEntry.COLUMN_STEP7,
                RecipeContract.RecipeEntry.COLUMN_STEP8,
                RecipeContract.RecipeEntry.COLUMN_STEP9,
                RecipeContract.RecipeEntry.COLUMN_STEP10};

        Cursor cursor = db.query(RecipeContract.RecipeEntry.TABLE_NAME, projection,null,null,null,null,null,null);

        //TextView displayView =  findViewById(R.id.textViewDatabase);

        try {
            // Display all the entries with the cursor

            displayView.setText("The Recipes table contains" + cursor.getCount() + " recipes.\n\n");
            displayView.append(RecipeContract.RecipeEntry.COLUMN_ID + " - "
                    + RecipeContract.RecipeEntry.COLUMN_NAME + " - "
                    + RecipeContract.RecipeEntry.COLUMN_INGREDIENT + " - "
                    + RecipeContract.RecipeEntry.COLUMN_CALORIES + " - "
                    + RecipeContract.RecipeEntry.COLUMN_TIME + "\n");

            //Figure out the id of each column
            int idColumnIndex = cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_ID);
            int nameColumnIndex = cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_NAME);
            int ingredColumnIndex = cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_INGREDIENT);
            int caloColumnIndex = cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_CALORIES);
            int timeColumnIndex = cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_TIME);
            int step1ColumnIndex = cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_STEP1);
            int step2ColumnIndex = cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_STEP2);
            int step3ColumnIndex = cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_STEP3);
            int step4ColumnIndex = cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_STEP4);
            int step5ColumnIndex = cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_STEP5);
            int step6ColumnIndex = cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_STEP6);
            int step7ColumnIndex = cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_STEP7);
            int step8ColumnIndex = cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_STEP8);
            int step9ColumnIndex = cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_STEP9);
            int step10ColumnIndex = cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_STEP10);



            while(cursor.moveToNext()) {

                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentIngred = cursor.getString(ingredColumnIndex);
                int currentCalories = cursor.getInt(caloColumnIndex);
                int currentTime = cursor.getInt(timeColumnIndex);
                String currentStep1 = cursor.getString(step1ColumnIndex);
                String currentStep2 = cursor.getString(step2ColumnIndex);
                String currentStep3 = cursor.getString(step3ColumnIndex);
                String currentStep4 = cursor.getString(step4ColumnIndex);
                String currentStep5 = cursor.getString(step5ColumnIndex);
                String currentStep6 = cursor.getString(step6ColumnIndex);
                String currentStep7 = cursor.getString(step7ColumnIndex);
                String currentStep8 = cursor.getString(step8ColumnIndex);
                String currentStep9 = cursor.getString(step9ColumnIndex);
                String currentStep10 = cursor.getString(step10ColumnIndex);



                displayView.append(currentID + " - "
                        + currentName + " - "
                        + currentIngred + " - "
                        + currentCalories + " - "
                        + currentTime + " - "
                        + currentStep1 + " - "
                        + currentStep2 + " - "
                        + currentStep3 + " - "
                        + currentStep4 + " - "
                        + currentStep5 + " - "
                        + currentStep6 + " - "
                        + currentStep7 + " - "
                        + currentStep8 + " - "
                        + currentStep9 + " - "
                        + currentStep10 + " \n ");


            }




        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }*/
}

