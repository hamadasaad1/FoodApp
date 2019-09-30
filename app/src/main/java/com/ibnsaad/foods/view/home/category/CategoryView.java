package com.ibnsaad.foods.view.home.category;

import com.ibnsaad.foods.model.Meals;

import java.util.List;

public interface CategoryView {
    void showLoading();
    void hideLoading();
    void onErrorMessage(String message);
    void setCategoryByMeal(List<Meals.Meal>meal);
}
