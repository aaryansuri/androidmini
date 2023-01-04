package com.example.hello.hello.Navigation;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hello.hello.DataBaseRecipe;
import com.example.hello.hello.ListAdapter;
import com.example.hello.hello.R;

import java.util.ArrayList;
import java.util.List;
import com.example.hello.hello.nutrientitem;

public class MyRecipiesActivity extends AppCompatActivity {

    DataBaseRecipe dataBaseRecipe;
    ListView listView;
    ListAdapter listAdapter;
    List<nutrientitem> nutrientitemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipies);
        listView = findViewById(R.id.list_view_id);
        nutrientitemList = new ArrayList<>();

        dataBaseRecipe = new DataBaseRecipe(this);

        SQLiteDatabase sqLiteDatabase = dataBaseRecipe.getReadableDatabase();
        String query = "Select name,calories from Recipes;";

        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        int nameIndex = cursor.getColumnIndex("name");
        int calorieIndex = cursor.getColumnIndex("calories");

        while (cursor.moveToNext()){
            String name = cursor.getString(nameIndex);
            int calories = cursor.getInt(calorieIndex);
            nutrientitemList.add(new nutrientitem(name,Integer.toString(calories).concat(" cal")));
        }

        listAdapter = new ListAdapter(getApplicationContext(),nutrientitemList);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int recipe_id = position;

                Intent intent = new Intent(MyRecipiesActivity.this,DisplayUserRecipe.class);
                intent.putExtra("RECIPE_ID",recipe_id);
                startActivity(intent);
            }
        });
       /* String ing ="";
        String steps="";
        int col = cursor.getColumnIndex("ingredients");
        int col1 = cursor.getColumnIndex("step");
        if(cursor.moveToFirst()){
            steps=cursor.getString(col1);
            ing=cursor.getString(col);
        }


        myrecipe.setText(ing);
        myrecipe.append(steps);
    */
    }
}
