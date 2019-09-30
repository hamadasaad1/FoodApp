package com.ibnsaad.foods.view.home.category;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.ibnsaad.foods.R;
import com.ibnsaad.foods.adapter.MealsByCategoryAdater;
import com.ibnsaad.foods.adapter.ViewPagerCategoryAdapter;
import com.ibnsaad.foods.model.Categories;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPagerCategories)
    ViewPager viewPagerCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);

        intiActionBar();
        intiIntent();

    }

    private void intiIntent() {

        Intent intent=getIntent();
        List<Categories.Category> categories=
                (List<Categories.Category>) intent.getSerializableExtra("EXTRA_CATEGORY");
        int position=intent.getIntExtra("EXTRA_POSITION",0);
        ViewPagerCategoryAdapter adapter=new
                ViewPagerCategoryAdapter(getSupportFragmentManager(),categories);
        viewPagerCategories.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPagerCategories);
        viewPagerCategories.setCurrentItem(position,true);
        adapter.notifyDataSetChanged();

    }

    private void intiActionBar() {

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}
