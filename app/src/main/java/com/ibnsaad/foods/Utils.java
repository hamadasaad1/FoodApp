package com.ibnsaad.foods;


import android.content.Context;

import androidx.appcompat.app.AlertDialog;

import com.ibnsaad.foods.api.FoodApi;
import com.ibnsaad.foods.api.FoodClient;

public class Utils {

    //built client to use
    public static FoodApi getApi(){
        return FoodClient.getClient().create(FoodApi.class);
    }


    //for show message error
    public static AlertDialog dialogShow(Context context,String title,String message){
        AlertDialog alertDialog=new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .show();
        if (alertDialog.isShowing()){
            alertDialog.cancel();
        }
        return alertDialog;

    }

}
