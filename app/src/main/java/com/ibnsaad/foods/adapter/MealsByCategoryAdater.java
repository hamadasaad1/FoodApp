package com.ibnsaad.foods.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ibnsaad.foods.R;
import com.ibnsaad.foods.model.Meals;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MealsByCategoryAdater extends
        RecyclerView.Adapter<MealsByCategoryAdater.MealHolder> {


    private Context context;
    private List<Meals.Meal> meals;
    private static ClickListener clickListener;

    public MealsByCategoryAdater(Context context, List<Meals.Meal> meals) {
        this.context = context;
        this.meals = meals;
    }


    @NonNull
    @Override
    public MealHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_meal
                , parent, false);
        return new MealHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealHolder holder, int position) {

        holder.mealName.setText(meals.get(position).getStrMeal());
        Picasso.get()
                .load(meals.get(position).getStrMealThumb())
                .placeholder(R.drawable.ic_circel)
                .into(holder.mealThumb);

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class MealHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.meal_thumb)
        ImageView mealThumb;
        @BindView(R.id.meal_Name)
        TextView mealName;

        public MealHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(), view);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        MealsByCategoryAdater.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);


    }

}
