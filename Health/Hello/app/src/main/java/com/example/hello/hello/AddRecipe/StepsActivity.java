package com.example.hello.hello.AddRecipe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.hello.hello.R;

public class StepsActivity extends AppCompatActivity {

    Button new_step;
    EditText editText[];
    LinearLayout linearLayout;
    int n = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        linearLayout = findViewById(R.id.linear_layout_id);

        new_step = findViewById(R.id.add_id);

        new_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n++;
                editText = new EditText[n];
                for(int i=0;i<n ;i++){

                    LinearLayout.LayoutParams Linearparams =  new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    editText[i] = new EditText(getApplicationContext());
                    editText[i].setLayoutParams(Linearparams);
                    editText[i].setHint("Ingredient "+(i+1));
                    linearLayout.addView(editText[i]);

                }

            }
        });
    }
}
