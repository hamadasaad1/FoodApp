package com.ibnsaad.foods.api;

import com.ibnsaad.foods.model.Categories;
import com.ibnsaad.foods.model.Meals;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface FoodApi {

    //this for get Categories
    @GET("categories.php")
    Call<Categories> getCategories();

    @GET("latest.php")
    Call<Meals> getMeals();
    @GET("filter.php")
    Call<Meals>getCategoryByMeals(@Query("c")String meals);

    @GET("search.php")
    Call<Meals> getMealByName(@Query("s") String mealName);


}
