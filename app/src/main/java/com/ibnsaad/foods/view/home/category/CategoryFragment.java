package com.ibnsaad.foods.view.home.category;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ibnsaad.foods.R;
import com.ibnsaad.foods.Utils;
import com.ibnsaad.foods.adapter.MealsByCategoryAdater;
import com.ibnsaad.foods.model.Meals;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryFragment extends Fragment implements CategoryView {

    @BindView(R.id.imageCategoryBg)
    ImageView imageCategoryBg;
    @BindView(R.id.imageCategory)
    ImageView imageCategory;
    @BindView(R.id.textCategory)
    TextView textCategory;
    @BindView(R.id.recycler_meal_category)
    RecyclerView recyclerMealCategory;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    AlertDialog.Builder descDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.category_fragment_layout,
                container, false);
        ButterKnife.bind(this, view);
        return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            textCategory.setText(getArguments().getString("CATEGORY_DSC"));
            Picasso.get()
                    .load(getArguments().getString("CATEGORY_THUMB"))
                    .placeholder(R.drawable.ic_circel)
                    .into(imageCategory);
            Picasso.get()
                    .load(getArguments().getString("CATEGORY_THUMB"))
                    .placeholder(R.drawable.ic_circel)
                    .into(imageCategoryBg);

            CategoryPresenter presenter=new CategoryPresenter(this);

            presenter.getCategory(getArguments().getString("CATEGORY_NAME"));

            descDialog=new AlertDialog.Builder(getActivity())
                    .setTitle(getArguments().getString("CATEGORY_NAME"))
            .setMessage(getArguments().getString("CATEGORY_DSC"));

        }


    }


    @Override
    public void showLoading() {

        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);


    }

    @Override
    public void onErrorMessage(String message) {
        Utils.dialogShow(getActivity(),"Title",message);

    }

    @Override
    public void setCategoryByMeal(final List<Meals.Meal> meal) {

        for (Meals.Meal meal1:meal){
            Log.d("Category>>",meal1.getStrMeal());
        }
        MealsByCategoryAdater adater=new MealsByCategoryAdater(getActivity(),meal);

        recyclerMealCategory.setAdapter(adater);
        GridLayoutManager manager=new GridLayoutManager(getActivity(),2);
        recyclerMealCategory.setLayoutManager(manager);
        recyclerMealCategory.setClipToPadding(false);
        adater.notifyDataSetChanged();
        adater.setOnItemClickListener(new MealsByCategoryAdater.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {

                Toast.makeText(getActivity(), "Meals"+meal.get(position).getStrMeal()
                        , Toast.LENGTH_SHORT).show();

            }
        });

    }
    @OnClick(R.id.card_category)
    public void onClick(){
        descDialog.setPositiveButton("Close", (dialogInterface, i) -> dialogInterface.dismiss());
        descDialog.show();
    }
}
