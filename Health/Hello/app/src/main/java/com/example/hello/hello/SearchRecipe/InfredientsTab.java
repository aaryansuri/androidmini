package com.example.hello.hello.SearchRecipe;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hello.hello.R;

public class InfredientsTab extends Fragment {


    public InfredientsTab() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View returnView =  inflater.inflate(R.layout.fragment_infredients_tab, container, false);
        final TextView textView = returnView.findViewById(R.id.hello_id);

        textView.setText(Details.getIngredients());

        return returnView;
    }
}
