package com.example.hello.hello;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends BaseAdapter {

    private Context context;
    private List<nutrientitem> nutrientitemList;

    public ListAdapter(Context context, List<nutrientitem> nutrientitemList) {
        this.context = context;
        this.nutrientitemList = nutrientitemList;
    }

    @Override
    public int getCount() {
        return nutrientitemList.size();
    }

    @Override
    public Object getItem(int position) {
        return nutrientitemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.itemview_list,null);
        TextView nutrient_name = view.findViewById(R.id.nutrient_name_id);
        TextView nutrient_amt = view.findViewById(R.id.nutrient_amt);

        nutrient_name.setText(nutrientitemList.get(position).getName());
        nutrient_amt.setText(nutrientitemList.get(position).getAmt());
        return view;
    }
}
