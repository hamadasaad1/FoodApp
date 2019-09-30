package com.ibnsaad.foods.view.home;

import androidx.annotation.Nullable;

import com.ibnsaad.foods.Utils;
import com.ibnsaad.foods.model.Categories;
import com.ibnsaad.foods.model.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter {

    private HomeView view;

    public HomePresenter(HomeView view) {
        this.view = view;
    }

    void getMeals(){
        view.showLoading();

        Call<Meals> call= Utils.getApi().getMeals();
        call.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(@Nullable Call<Meals> call,@Nullable Response<Meals> response) {
                view.hideLoading();
                if (response.isSuccessful()&&response.body() !=null){
                    view.setMeals(response.body().getMeals());

                }else {
                    view.showMessageError(response.message());
                }
            }

            @Override
            public void onFailure(@Nullable Call<Meals> call,@Nullable Throwable t) {

                view.hideLoading();
                view.showMessageError(t.getLocalizedMessage());
            }
        });

    }
    void getCategories(){

        view.showLoading();
        Call<Categories> call=Utils.getApi().getCategories();
        call.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(@Nullable Call<Categories> call,@Nullable Response<Categories> response) {
                view.hideLoading();
                if (response.isSuccessful()&&response.body() !=null){
                    view.setCategories(response.body().getCategories());
                }else {
                    view.showMessageError(response.message());
                }
            }

            @Override
            public void onFailure(@Nullable Call<Categories> call,@Nullable Throwable t) {

                view.hideLoading();
                view.showMessageError(t.getLocalizedMessage());
            }
        });
    }
}
