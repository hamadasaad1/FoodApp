package com.ibnsaad.foods.adapter;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ibnsaad.foods.model.Categories;

import com.ibnsaad.foods.view.home.category.CategoryFragment;

import java.util.List;

public class ViewPagerCategoryAdapter extends FragmentPagerAdapter {

    List<Categories.Category>categories;

    public ViewPagerCategoryAdapter(FragmentManager fm,
                                    List<Categories.Category>categories) {
        super(fm);
        this.categories=categories;
    }

    @Override
    public Fragment getItem(int position) {
        CategoryFragment fragment=new CategoryFragment();
        Bundle bundle=new Bundle();
        bundle.putString("CATEGORY_NAME",categories.get(position).getStrCategory());
        bundle.putString("CATEGORY_DSC",categories.get(position).getStrCategoryDescription());
        bundle.putString("CATEGORY_THUMB",categories.get(position).getStrCategoryThumb());

        fragment.setArguments(bundle);
        return fragment;


    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return categories.get(position).getStrCategory();
    }
}
