package com.ibnsaad.foods.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ibnsaad.foods.R;
import com.ibnsaad.foods.model.Categories;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerHomeAdapter extends RecyclerView.Adapter<RecyclerHomeAdapter.HolderView> {


    private Context context;
    private List<Categories.Category> categories;
    private static ClickListener clickListener;

    public RecyclerHomeAdapter(Context context, List<Categories.Category> categories) {

        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public HolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler_home_adapter
                , parent, false);
        return new HolderView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderView holder, int position) {

           holder.textCategoryName.setText(categories.get(position).getStrCategory());
        Picasso.get()
                .load(categories.get(position).getStrCategoryThumb())
                .placeholder(R.drawable.ic_circel)
                .into(holder.imageCategory);
    }

    @Override
    public int getItemCount() {

        return categories.size();
    }

    public class HolderView extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.image_category)
        ImageView imageCategory;
        @BindView(R.id.text_Category_name)
        TextView textCategoryName;

        public HolderView(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(), view);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        RecyclerHomeAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);

        void onItemLongClick(int position, View v);
    }
}
