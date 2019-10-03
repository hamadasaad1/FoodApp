package com.ibnsaad.foods.view.home.details;

import com.ibnsaad.foods.model.Meals;

import java.util.List;

public interface DetailsView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void setMeal(List<Meals.Meal>meals);
}
