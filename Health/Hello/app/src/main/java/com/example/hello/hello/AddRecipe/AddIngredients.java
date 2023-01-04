package com.example.hello.hello.AddRecipe;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.hello.hello.DataBaseRecipe;
import com.example.hello.hello.R;

import java.util.ArrayList;
import java.util.List;

public class AddIngredients extends Fragment {

    FloatingActionButton floatingActionButton;
    View view;
    LinearLayout linearLayout;
    EditText editText;
    static List<EditText> editTexts_ing = new ArrayList<>();
    ImageView delete;
    DataBaseRecipe mDbHelper;

    public AddIngredients(){}

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.add_ingredients_fragment,container,false);
        floatingActionButton = view.findViewById(R.id.floatingActionButton_id);
        linearLayout = view.findViewById(R.id.linear_layout_id);
        delete = view.findViewById(R.id.delete_ingredient_id);
        mDbHelper = new DataBaseRecipe(getContext());

        final LinearLayout.LayoutParams LP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);

        LP.setMargins(20,30,20,0);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText = new EditText(getContext());
                editText.setLayoutParams(LP);
                editText.setMaxLines(1);
                editText.setHint("Add Ingredient");
                editText.setGravity(Gravity.CENTER);
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                editText.requestFocus();
                editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_black_24dp, 0);

                editText.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        final int DRAWABLE_LEFT = 0;
                        final int DRAWABLE_TOP = 1;
                        final int DRAWABLE_RIGHT = 2;
                        final int DRAWABLE_BOTTOM = 3;
                        for (EditText e : editTexts_ing) {
                            if (e.getId() == view.getId()) {
                                if (event.getAction() == MotionEvent.ACTION_UP) {
                                    if (event.getX() >= (e.getRight() - e.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                                        editTexts_ing.remove(e);
                                        linearLayout.removeView(e);
                                        return true;
                                    }

                                }
                                return false;
                            }
                        }
                        return false;
                    }

        });
                linearLayout.addView(editText);
                editTexts_ing.add(editText);


    }
    });
    delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        linearLayout.removeAllViewsInLayout();
                        editTexts_ing.clear();
                    }


                });
        return view;
    }



}
