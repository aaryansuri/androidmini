package com.example.hello.hello;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hello.hello.SearchRecipe.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context recipe_context;
    private List<Recipe> recipe_data;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
            onItemClickListener = listener;
    }

    public RecyclerViewAdapter(Context recipe_context, List<Recipe> recipe_data) {
        this.recipe_context = recipe_context;
        this.recipe_data = recipe_data;
        setHasStableIds(true);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(recipe_context);
        view = layoutInflater.inflate(R.layout.cardview_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.recipe_name.setText(recipe_data.get(position).getRecipe_name());
        Picasso.get().load(recipe_data.get(position).getRecipe_image()).fit().into(holder.recipe_image);
        float cal = Float.parseFloat(recipe_data.get(position).getRecipe_calories());
        String color;
        if(cal>1200) {
            if(cal>1600 && cal <2200)
            {
                color = "#FD0026";
            }else if(cal>=2200) {
                color = "#C70421";
            }else
            {
                color="#F73A56";
            }
        }else
        {
            if(cal < 1000) {
                color="#10F70B";
            }else
            {
                color="#06CA02";
            }
        }
        holder.recipe_calories.setTextColor(Color.parseColor(color));
        holder.recipe_calories.setText(recipe_data.get(position).getRecipe_calories().concat(" Cal  "));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getItemCount() {
        return recipe_data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView recipe_name;
        ImageView recipe_image;
        TextView recipe_calories;

        public MyViewHolder(View itemView) {
            super(itemView);
            recipe_name = itemView.findViewById(R.id.recipe_name_id);
            recipe_image = itemView.findViewById(R.id.image_id);
            recipe_calories = itemView.findViewById(R.id.calories_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener!=null){
                        int pos = getAdapterPosition();

                        if(pos!=RecyclerView.NO_POSITION){
                            onItemClickListener.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
