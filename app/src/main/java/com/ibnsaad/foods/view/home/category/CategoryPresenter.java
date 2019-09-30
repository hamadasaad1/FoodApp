package com.ibnsaad.foods.view.home.category;

import androidx.annotation.Nullable;

import com.ibnsaad.foods.Utils;
import com.ibnsaad.foods.model.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryPresenter {

    CategoryView view;

    public CategoryPresenter(CategoryView view) {
        this.view = view;
    }

    public void getCategory(String category){
        view.showLoading();
        Call<Meals> call= Utils.getApi().getCategoryByMeals(category);
        call.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(@Nullable Call<Meals> call,@Nullable Response<Meals> response) {
                view.hideLoading();
                if (response.isSuccessful()&&response.body() !=null){

                    view.setCategoryByMeal(response.body().getMeals());

                }else {
                    view.onErrorMessage(response.message());
                }
            }

            @Override
            public void onFailure(@Nullable Call<Meals> call,@Nullable Throwable t) {

                view.hideLoading();
                view.onErrorMessage(t.getLocalizedMessage());
            }
        });

    }
}
