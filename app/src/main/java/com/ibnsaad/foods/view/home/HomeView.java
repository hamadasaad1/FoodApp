package com.ibnsaad.foods.view.home;

import com.ibnsaad.foods.model.Categories;
import com.ibnsaad.foods.model.Meals;

import java.util.List;

public interface HomeView {

    void showLoading();
    void hideLoading();
    void setMeals(List<Meals.Meal>meal);
    void setCategories(List<Categories.Category>category);
    void showMessageError(String message);
}
