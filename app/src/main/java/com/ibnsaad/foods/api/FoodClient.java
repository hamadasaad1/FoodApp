package com.ibnsaad.foods.api;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodClient {

    private static final String BASEURL="https://www.themealdb.com/api/json/v1/1/";
    private static Retrofit retrofit=null;

    public static Retrofit getClient(){

        retrofit=new Retrofit.Builder()
                .baseUrl(BASEURL)
                .client(client())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
    private static Interceptor provideLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }


    private static OkHttpClient client() {

        return   new
                OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30,TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .addInterceptor(provideLoggingInterceptor()).build();
    }

}
