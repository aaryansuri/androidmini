package com.example.hello.hello.SearchRecipe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hello.hello.R;
import com.example.hello.hello.RecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchRecipeActivity extends AppCompatActivity implements RecyclerViewAdapter.OnItemClickListener {

    Button searchbutton;
    EditText searchtext;
    List<Recipe> recipe_list;
    RecyclerView recyclerView;
    LinearLayoutManager manager;
    RecyclerViewAdapter adapter;
    Boolean is_scrolling=false;
    int lim0,lim1;
    int current_items,total_items,scrolled_items;
    static SearchRecipeActivity searchRecipeActivity;
    StringRequest stringRequest;
    Button float_Button;
    View v;
    public static final String EXTRA_NAME= "name";
    public static final String LIM0= "lim0";
    public static final String LIM1= "lim1";
    public static final String POSITION= "pos";
    public static final String IMAGEURL= "imageurl";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchRecipeActivity = this;
        setContentView(R.layout.searchrecipe_main);
        recyclerView = findViewById(R.id.recyclerview_id);
        manager = new LinearLayoutManager(this);
        searchbutton =  findViewById(R.id.search_button_id);
        searchtext =  findViewById(R.id.search_text_id);
        float_Button = findViewById(R.id.floatingActionButton);
        recipe_list = new ArrayList<>();
        v = findViewById(R.id.srch_id);

        float_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float_Button.setVisibility(View.INVISIBLE);
                addnewItems();
            }
        });

        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipe_list.clear();
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(searchtext.getWindowToken(), 0);
                    lim0=0;
                    lim1=10;
                    Json_ADD(lim0,lim1);


                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        if(newState==AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                            is_scrolling=true;
                            float_Button.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        current_items = manager.getChildCount();
                        total_items = manager.getItemCount();
                        scrolled_items = manager.findFirstVisibleItemPosition();

                        if(is_scrolling && (current_items + scrolled_items == total_items)){
                            is_scrolling = false;
                            float_Button.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        });
    }

     public void Json_ADD(int lim0,int lim1) {

            String url = "https://api.edamam.com/search?q="+searchtext.getText().toString()
                    +"&app_id="+APIKEY.getApiId()+"&app_key="+APIKEY.getAPI_key()+"&from="+lim0+"&to="+lim1;


            stringRequest = new StringRequest(Request.Method.GET,url,new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        Log.i("hello",response);
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("hits");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            JSONObject jsonObject2 = jsonObject1.getJSONObject("recipe");
                            String recipe_name = jsonObject2.getString("label");
                            String calories = jsonObject2.getString("calories").substring(0, 4);
                            String image_url = jsonObject2.getString("image");
                            recipe_list.add(new Recipe(recipe_name, image_url, calories));
                        }
                        adapter = new RecyclerViewAdapter(SearchRecipeActivity.this,recipe_list);
                        recyclerView.setLayoutManager(manager);
                        recyclerView.setAdapter(adapter);
                        adapter.setOnItemClickListener(SearchRecipeActivity.this);
                        adapter.notifyDataSetChanged();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Snackbar snackbar;
                    snackbar=Snackbar.make(v,"API Limits Restriction "+error.toString(),Snackbar.LENGTH_LONG);

                    View vw = snackbar.getView();
                    vw.setBackgroundColor(getResources().getColor(R.color.white));

                    snackbar.show();
                }
            });

         RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
         requestQueue.add(stringRequest);


    }


    private void addnewItems() {
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        lim0 = lim0 +10;
                        lim1 = lim1 +10;
                        Json_ADD(lim0,lim1);
                    }
                }, 2000);
    }

    @Override
    public void onItemClick(int position) {
        final Intent detailIntent = new Intent(SearchRecipeActivity.this,Recipe_Details.class);
        Recipe recipe = recipe_list.get(position);

        detailIntent.putExtra(EXTRA_NAME,recipe.getRecipe_name());
        detailIntent.putExtra(IMAGEURL,recipe.getRecipe_image());
        Details.loadData(recipe.getRecipe_name(),Integer.toString(lim0),Integer.toString(lim1),Integer.toString(position),getApplicationContext());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(detailIntent);
            }
        },3000);


    }
}
