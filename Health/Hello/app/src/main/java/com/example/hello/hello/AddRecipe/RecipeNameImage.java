package com.example.hello.hello.AddRecipe;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hello.hello.DataBaseRecipe;
import com.example.hello.hello.R;
import com.example.hello.hello.RecipeContract;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

public class RecipeNameImage extends Fragment {

    View view;
    EditText recipe_name;
    Button done;
    public static int total_cal ;
    static HashMap<String, String> cal_per = new HashMap<>();
    DataBaseRecipe mDbHelper;
    EditText time;
    SQLiteDatabase db;
    static ContentValues values;
    ImageButton cameraButton;
    static StringBuilder fullIngredients;
    static StringBuilder fullSteps;
    public static final int PICK_IMAGE = 1 ;
     String picturePath;


    public RecipeNameImage() {
    }


    public void addMyRecipeDatabase(StringBuilder fullIngredients,StringBuilder fullSteps){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RecipeContract.RecipeEntry.COLUMN_NAME,recipe_name.getText().toString());
        values.put(RecipeContract.RecipeEntry.COLUMN_CALORIES,total_cal);
        values.put(RecipeContract.RecipeEntry.COLUMN_TIME,Integer.parseInt(time.getText().toString()));
        values.put(RecipeContract.RecipeEntry.COLUMN_INGREDIENT,fullIngredients.toString());
        values.put(RecipeContract.RecipeEntry.COLUMN_STEP,fullSteps.toString());
        values.put(RecipeContract.RecipeEntry.COLUMN_IMAGE_PATH,picturePath);
        db.insert(RecipeContract.RecipeEntry.TABLE_NAME,null,values);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.give_recipe_name_and_image, container, false);
        recipe_name = view.findViewById(R.id.recipe_name_id);
        done = view.findViewById(R.id.done_id);
        time = view.findViewById(R.id.time_id);
        cameraButton = view.findViewById(R.id.camera_id);
        mDbHelper = new DataBaseRecipe(getContext());
        db = mDbHelper.getWritableDatabase();
        fullIngredients = new StringBuilder();
        fullSteps = new StringBuilder();
        total_cal=0;
        values = new ContentValues();


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Intent intent = new Intent(getActivity(), FinalMyRecipeDetails.class);
                for (int i = 0; i < AddIngredients.editTexts_ing.size(); i++) {
                    GetCalorie(AddIngredients.editTexts_ing.get(i).getText().toString());
                    fullIngredients.append(AddIngredients.editTexts_ing.get(i).getText().toString());
                    fullIngredients.append("#");
                }




                for (int i=0;i<AddSteps.editTexts_steps.size();i++){
                    fullSteps.append(AddSteps.editTexts_steps.get(i).getText().toString());
                    fullSteps.append("#");
                }

                intent.putExtra("picture_path",picturePath);
                intent.putExtra("name", recipe_name.getText().toString());

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        addMyRecipeDatabase(fullIngredients,fullSteps);
                        fullIngredients.setLength(0);
                        fullSteps.setLength(0);
                        startActivity(intent);
                    }
                }, 3000);

            }
        });


        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,PICK_IMAGE);

            }
        });


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==PICK_IMAGE && resultCode == Activity.RESULT_OK && null!=data){

            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getActivity().getContentResolver().query(selectedImage,filePathColumn,null,null,null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();

            cameraButton.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }

    public void GetCalorie(final String item) {
        String URL = "https://api.edamam.com/api/nutrition-data?app_id=" + APIKEY1.getApiId() + "&app_key=" + APIKEY1.getAPI_key() + "&ingr=" + item;



        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String cal = jsonObject.getString("calories");
                    cal_per.put(item, cal);
                    Log.i("HELLO", item);
                    total_cal += Integer.parseInt(cal);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Error" + error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);


    }

}
