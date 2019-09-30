package com.ibnsaad.foods.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.ibnsaad.foods.R;
import com.ibnsaad.foods.Utils;
import com.ibnsaad.foods.adapter.RecyclerHomeAdapter;
import com.ibnsaad.foods.adapter.ViewPagerHeader;
import com.ibnsaad.foods.model.Categories;
import com.ibnsaad.foods.model.Meals;
import com.ibnsaad.foods.view.home.category.CategoryActivity;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeView {
    //make runnable for make view pager auto move

    private Handler handler;
    private int delay = 5000; //milliseconds
    private int page = 0;
    Runnable runnable= new Runnable() {
        public void run() {
            if (adapter.getCount() == page) {
                page = 0;
            } else {
                page++;
            }
            headerViewPager.setCurrentItem(page, true);
            handler.postDelayed(this, delay);
        }
    };

    HomePresenter presenter;
    ViewPagerHeader adapter;
    @BindView(R.id.header_view_pager)
    ViewPager headerViewPager;
    @BindView(R.id.recycler_view_category)
    RecyclerView recyclerViewCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        handler = new Handler();
        presenter = new HomePresenter(this);
        presenter.getCategories();
        presenter.getMeals();
    }

    @Override
    public void showLoading() {
        findViewById(R.id.shimmerCategory).setVisibility(View.VISIBLE);
        findViewById(R.id.shimmerMeal).setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoading() {
        findViewById(R.id.shimmerCategory).setVisibility(View.GONE);
        findViewById(R.id.shimmerMeal).setVisibility(View.GONE);
    }

    @Override
    public void setMeals(final List<Meals.Meal> meal) {

        //check for meal result
        for (Meals.Meal mealResult:meal){
            Log.d("Meals>>",mealResult.getStrMeal());
        }

         adapter=new ViewPagerHeader(this,meal);
        headerViewPager.setAdapter(adapter);
        headerViewPager.setPadding(20,0,150,0);
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener(new ViewPagerHeader.ClickListener() {
            @Override
            public void ClickItem(View view, int position) {
                Toast.makeText(HomeActivity.this, meal.get(position).getStrCategory()
                        , Toast.LENGTH_SHORT).show();
            }
        });



        //for make pager auto move
        headerViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                page=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void setCategories(final List<Categories.Category> category) {

        RecyclerHomeAdapter adapter=new RecyclerHomeAdapter(this,category);
        recyclerViewCategory.setAdapter(adapter);
        GridLayoutManager manager=new GridLayoutManager(this,3,
                GridLayoutManager.VERTICAL,false);

        recyclerViewCategory.setLayoutManager(manager);
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener(new RecyclerHomeAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Toast.makeText(HomeActivity.this, category.get(position).getStrCategory()
                        , Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(HomeActivity.this, CategoryActivity.class);
                intent.putExtra("EXTRA_CATEGORY", (Serializable) category);
                intent.putExtra("EXTRA_POSITION",position);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(int position, View v) {
            }
        });

    }

    @Override
    public void showMessageError(String message) {

        Utils.dialogShow(this, "Title", message);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable,delay);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }
}
