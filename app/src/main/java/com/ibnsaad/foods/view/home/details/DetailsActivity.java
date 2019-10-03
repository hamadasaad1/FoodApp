package com.ibnsaad.foods.view.home.details;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.ibnsaad.foods.R;
import com.ibnsaad.foods.Utils;
import com.ibnsaad.foods.model.Meals;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity implements DetailsView {

    @BindView(R.id.mealThumb)
    ImageView mealThumb;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.textCategory)
    TextView textCategory;
    @BindView(R.id.category)
    TextView category;
    @BindView(R.id.textCountry)
    TextView textCountry;
    @BindView(R.id.country)
    TextView country;
    @BindView(R.id.instructions)
    TextView instructions;
    @BindView(R.id.ingredient)
    TextView ingredient;
    @BindView(R.id.measure)
    TextView measure;
    @BindView(R.id.youTub)
    TextView youTub;
    @BindView(R.id.source)
    TextView source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        setUpAction();
        Intent intent=getIntent();
        if (intent !=null){
            String mealName=intent.getStringExtra("EXTRA_DETAILS");
            DetailsPresenter presenter=new DetailsPresenter(this);
            presenter.getMealByName(mealName);
        }

    }

    private void setUpAction(){
        setSupportActionBar(toolbar);
        collapsingToolbar.setContentScrimColor(getResources().getColor(R.color.colorWhite));
        collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.colorPrimary));
        collapsingToolbar.setExpandedTitleColor(getResources().getColor(R.color.colorWhite));
        if (getSupportActionBar() !=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onErrorLoading(String message) {

        Utils.dialogShow(this,"Title",message);
    }

    @Override
    public void setMeal(List<Meals.Meal> meals) {

        Log.d("Details>>",meals.get(0).getStrMeal());
        for (Meals.Meal meal: meals){
            Picasso.get().load(meal.getStrMealThumb()).into(mealThumb);
            collapsingToolbar.setTitle(meal.getStrMeal());
            country.setText(meal.getStrArea());
            category.setText(meal.getStrCategory());
            instructions.setText(meal.getStrInstructions());
            Log.d("Details","Details>>"+meal.getStrSource());

            youTub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 playVideo(meal.getStrYoutube());
                }
            });

            getIngredient(meal);
            getMeasure(meal);

            source.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                }
            });

        }
    }
    private void playVideo(String key){

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + key));

        // Check if the youtube app exists on the device
        if (intent.resolveActivity(getPackageManager()) == null) {
            // If the youtube app doesn't exist, then use the browser
            intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=" + key));
        }
        intent.putExtra("videoId",key);

        startActivity(intent);
    }

    private void getIngredient(Meals.Meal meal){

        if (!meal.getStrIngredient1().isEmpty()){
            ingredient.append("\n \u2022"+meal.getStrIngredient1());

        }if  (!meal.getStrIngredient2().isEmpty()) {
            ingredient.append("\n \u2022"+meal.getStrIngredient2());

        }  if(!meal.getStrIngredient3().isEmpty()) {
            ingredient.append("\n \u2022"+meal.getStrIngredient3());
        }
         if(!meal.getStrIngredient4().isEmpty()) {
             ingredient.append("\n \u2022"+meal.getStrIngredient4());
        }
         if(!meal.getStrIngredient5().isEmpty()) {
             ingredient.append("\n \u2022"+meal.getStrIngredient5());
        }
         if(!meal.getStrIngredient6().isEmpty()) {

             ingredient.append("\n \u2022"+meal.getStrIngredient6());
        }
         if(!meal.getStrIngredient7().isEmpty()) {
             ingredient.append("\n \u2022"+meal.getStrIngredient7());
        }
         if(!meal.getStrIngredient8().isEmpty()) {
             ingredient.append("\n \u2022"+meal.getStrIngredient8());
        }
        if(!meal.getStrIngredient9().isEmpty()) {
            ingredient.append("\n \u2022"+meal.getStrIngredient9());
        }
        if(!meal.getStrIngredient10().isEmpty()) {
            ingredient.append("\n \u2022"+meal.getStrIngredient10());
        }
        if(!meal.getStrIngredient11().isEmpty()) {
            ingredient.append("\n \u2022"+meal.getStrIngredient11());
        }
        if(!meal.getStrIngredient12().isEmpty()) {
            ingredient.append("\n \u2022"+meal.getStrIngredient12());
        }
        if(!meal.getStrIngredient13().isEmpty()) {
            ingredient.append("\n \u2022"+meal.getStrIngredient13());
        }
        if(!meal.getStrIngredient14().isEmpty()) {
            ingredient.append("\n \u2022"+meal.getStrIngredient14());
        }
        if(!meal.getStrIngredient15().isEmpty()) {
            ingredient.append("\n \u2022"+meal.getStrIngredient15());
        }
        if(!meal.getStrIngredient16().isEmpty()) {
            ingredient.append("\n \u2022"+meal.getStrIngredient16());
        }
        if(!meal.getStrIngredient17().isEmpty()) {
            ingredient.append("\n \u2022"+meal.getStrIngredient17());
        }
        if(!meal.getStrIngredient18().isEmpty()) {
            ingredient.append("\n \u2022"+meal.getStrIngredient18());
        }
        if(!meal.getStrIngredient19().isEmpty()) {
            ingredient.append("\n \u2022"+meal.getStrIngredient19());
        }
        if(!meal.getStrIngredient20().isEmpty()) {
            ingredient.append("\n \u2022 "+meal.getStrIngredient20());
        }

       }

    private void  getMeasure(Meals.Meal meal){
        if(!meal.getStrMeasure1().isEmpty() && !Character.isWhitespace(meal.getStrMeasure1().charAt(0))) {
            measure.append("\n"+meal.getStrMeasure1());
        }
        if(!meal.getStrMeasure2().isEmpty() && !Character.isWhitespace(meal.getStrMeasure2().charAt(0))) {
            measure.append("\n"+meal.getStrMeasure2());
        }
        if(!meal.getStrMeasure3().isEmpty() && !Character.isWhitespace(meal.getStrMeasure3().charAt(0))) {
            measure.append("\n"+meal.getStrMeasure3());
        }
        if(!meal.getStrMeasure4().isEmpty() && !Character.isWhitespace(meal.getStrMeasure4().charAt(0))) {
            measure.append("\n"+meal.getStrMeasure4());
        }
        if(!meal.getStrMeasure5().isEmpty() && !Character.isWhitespace(meal.getStrMeasure5().charAt(0))) {
            measure.append("\n"+meal.getStrMeasure5());
        }
        if(!meal.getStrMeasure6().isEmpty() && !Character.isWhitespace(meal.getStrMeasure6().charAt(0))) {
            measure.append("\n"+meal.getStrMeasure6());
        }
        if(!meal.getStrMeasure7().isEmpty() && !Character.isWhitespace(meal.getStrMeasure7().charAt(0))) {
            measure.append("\n"+meal.getStrMeasure7());
        }
        if(!meal.getStrMeasure8().isEmpty() && !Character.isWhitespace(meal.getStrMeasure8().charAt(0))) {
            measure.append("\n"+meal.getStrMeasure8());
        }
        if(!meal.getStrMeasure9().isEmpty() && !Character.isWhitespace(meal.getStrMeasure9().charAt(0))) {
            measure.append("\n"+meal.getStrMeasure9());
        }
        if(!meal.getStrMeasure10().isEmpty() && !Character.isWhitespace(meal.getStrMeasure10().charAt(0))) {
            measure.append("\n"+meal.getStrMeasure10());
        }
        if(!meal.getStrMeasure11().isEmpty() && !Character.isWhitespace(meal.getStrMeasure11().charAt(0))) {
            measure.append("\n"+meal.getStrMeasure11());
        }
        if(!meal.getStrMeasure12().isEmpty() && !Character.isWhitespace(meal.getStrMeasure12().charAt(0))) {
            measure.append("\n"+meal.getStrMeasure12());
        }
        if(!meal.getStrMeasure14().isEmpty() && !Character.isWhitespace(meal.getStrMeasure14().charAt(0))) {
            measure.append("\n"+meal.getStrMeasure14());
        }
        if(!meal.getStrMeasure15().isEmpty() && !Character.isWhitespace(meal.getStrMeasure15().charAt(0))) {
            measure.append("\n"+meal.getStrMeasure15());
        }
        if(!meal.getStrMeasure16().isEmpty() && !Character.isWhitespace(meal.getStrMeasure16().charAt(0))) {
            measure.append("\n"+meal.getStrMeasure16());
        }
        if(!meal.getStrMeasure17().isEmpty() && !Character.isWhitespace(meal.getStrMeasure17().charAt(0))) {
            measure.append("\n"+meal.getStrMeasure17());
        }
        if(!meal.getStrMeasure18().isEmpty() && !Character.isWhitespace(meal.getStrMeasure18().charAt(0))) {
            measure.append("\n"+meal.getStrMeasure18());
        }
        if(!meal.getStrMeasure19().isEmpty() && !Character.isWhitespace(meal.getStrMeasure19().charAt(0))) {
            measure.append("\n"+meal.getStrMeasure19());
        }
        if(!meal.getStrMeasure20().isEmpty() && !Character.isWhitespace(meal.getStrMeasure20().charAt(0))) {
            measure.append("\n"+meal.getStrMeasure20());
        }
    }




}
