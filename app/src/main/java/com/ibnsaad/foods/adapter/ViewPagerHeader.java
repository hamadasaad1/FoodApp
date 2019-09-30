package com.ibnsaad.foods.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.ibnsaad.foods.R;
import com.ibnsaad.foods.model.Meals;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPagerHeader extends PagerAdapter {

    @BindView(R.id.mealThumb)
    ImageView mealThumb;
    @BindView(R.id.mealName)
    TextView mealName;
    private Context context;
    private List<Meals.Meal> meals;
    private static ClickListener clickListener;

    public ViewPagerHeader(Context context, List<Meals.Meal> meals) {
        this.context = context;
        this.meals = meals;
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        ViewPagerHeader.clickListener = clickListener;
    }


    @Override
    public int getCount() {
        return meals.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View itemView = LayoutInflater.from(context).
                inflate(R.layout.layout_view_pager_meals, container, false);
        ButterKnife.bind(this,itemView);

        mealName.setText(meals.get(position).getStrMeal());

        String strMealThumb = meals.get(position).getStrMealThumb();
        Picasso.get().load(strMealThumb).into(mealThumb);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.ClickItem(view,position);
            }
        });
        container.addView(itemView,0);

        return itemView;

    }

    public interface ClickListener {
        void ClickItem(View view, int position);
    }
}
