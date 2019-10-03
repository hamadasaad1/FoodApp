package com.ibnsaad.foods.view.home.details;

import androidx.annotation.Nullable;

import com.ibnsaad.foods.Utils;
import com.ibnsaad.foods.model.Meals;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsPresenter {
    DetailsView view;

    public DetailsPresenter(DetailsView view) {
        this.view = view;
    }

    public void getMealByName(String mealName){
        view.showLoading();
        Utils.getApi().getMealByName(mealName)
                .enqueue(new Callback<Meals>() {
                    @Override
                    public void onResponse(@Nullable Call<Meals> call,@Nullable Response<Meals> response) {
                        view.hideLoading();
                        if (response.isSuccessful() && response.body() !=null){

                            view.setMeal(Collections.singletonList(response.body().getMeals().get(0)));
                        }else {
                            view.onErrorLoading(response.message());
                        }

                    }

                    @Override
                    public void onFailure(@Nullable Call<Meals> call,@Nullable Throwable t) {

                        view.hideLoading();
                        view.onErrorLoading(t.getLocalizedMessage());
                    }
                });

    }
}
