package com.example.hello.hello.SearchRecipe;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hello.hello.ListAdapter;
import com.example.hello.hello.R;
import com.example.hello.hello.nutrientitem;

import java.util.ArrayList;
import java.util.List;


public class FragmentNutrition extends Fragment {


    ListView listView;
    ImageView share;
    ListAdapter listAdapter;
    List<nutrientitem> nutrientlist;
    TextView calorie,carb,weight;
    EditText servings;
    View rl;
    Snackbar snackbar;

    public FragmentNutrition() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_fragment_nutrition, container, false);
        rl = view.findViewById(R.id.rl_id);

        share = view.findViewById(R.id.share_id);
         carb = view.findViewById(R.id.carb_id);
        carb.setText(Details.getCarblevel());
         weight = view.findViewById(R.id.weight_id);
        weight.setText(Details.getWeight());
        calorie = view.findViewById(R.id.calories_id);
        calorie.setText(Details.getCalorie());
        listView = view.findViewById(R.id.list_view_id);
        servings = view.findViewById(R.id.servings_id);
        nutrientlist = new ArrayList<>();

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int calories = Integer.parseInt(Details.getCalorie());

                String den= servings.getText().toString();
                int denom=1;
                if(!TextUtils.isEmpty(den)) {

                    denom = Integer.parseInt(den);
                }
                if(denom==0){
                    snackbar=Snackbar.make(rl,"0 not allowed",Snackbar.LENGTH_LONG);
                    View v = snackbar.getView();
                    v.setBackgroundColor(getResources().getColor(R.color.yellow));
                    snackbar.show();

                    snackbar.setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            snackbar.dismiss();
                        }
                    });

                }else {
                    calories = calories / denom;
                }
                calorie.setText(Integer.toString(calories));
            }
        };

        servings.addTextChangedListener(textWatcher);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String share_body = " Hey have a look at my favourite recipe \n" + Details.getWebsiteurl();
                shareIntent.putExtra(Intent.EXTRA_TEXT,share_body);
                startActivity(Intent.createChooser(shareIntent,"Share Using"));
            }
        });

        nutrientlist.add(new nutrientitem("Energy",Details.getEnergy()));
        nutrientlist.add(new nutrientitem("Fat",Details.getFat()));
        nutrientlist.add(new nutrientitem("Sugar",Details.getSugar()));
        nutrientlist.add(new nutrientitem("Carbs",Details.getCarbs()));
        nutrientlist.add(new nutrientitem("Protein",Details.getProtein()));
        nutrientlist.add(new nutrientitem("Cholestrol",Details.getCholestrol()));
        nutrientlist.add(new nutrientitem("Sodium",Details.getSodium()));
        nutrientlist.add(new nutrientitem("Magnesium",Details.getMangnesium()));
        nutrientlist.add(new nutrientitem("Iron",Details.getIron()));
        nutrientlist.add(new nutrientitem("Zinc",Details.getZinc()));
        nutrientlist.add(new nutrientitem("Vitamin A",Details.getVitaminA()));
        nutrientlist.add(new nutrientitem("Vitamin C",Details.getVitaminC()));
        nutrientlist.add(new nutrientitem("Vitamin B6",Details.getVitaminB6()));
        nutrientlist.add(new nutrientitem("Vitamin D",Details.getVitaminD()));
        nutrientlist.add(new nutrientitem("Vitamin B12",Details.getVitaminB12()));
        nutrientlist.add(new nutrientitem("Vitamin K",Details.getVitaminK()));



        listAdapter = new ListAdapter(getActivity().getApplicationContext(),nutrientlist);
        listView.setAdapter(listAdapter);



        return view;
    }
}
