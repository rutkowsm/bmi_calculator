package com.example.bmi_calculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    private Context context;
    private List<Recipe> recipes;
    private OnRecipeListener onRecipeListener;

    public RecipeAdapter(Context context, List<Recipe> recipes, OnRecipeListener onRecipeListener) {
        this.context = context;
        this.recipes = recipes;
        this.onRecipeListener = onRecipeListener;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_design, parent, false);
        return new RecipeViewHolder(view, onRecipeListener);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.textViewCountryName.setText(recipe.getName());
        holder.textViewDetail.setText(recipe.getDescription());
        holder.imageView.setImageResource(recipe.getImageResourceId());
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    // Definicja klasy ViewHolder
    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textViewCountryName, textViewDetail;
        public ImageView imageView;
        OnRecipeListener onRecipeListener;

        public RecipeViewHolder(View itemView, OnRecipeListener onRecipeListener) {
            super(itemView);
            textViewCountryName = itemView.findViewById(R.id.textViewCountryName);
            textViewDetail = itemView.findViewById(R.id.textViewDetail);
            imageView = itemView.findViewById(R.id.imageView);
            this.onRecipeListener = onRecipeListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onRecipeListener.onRecipeClick(getAdapterPosition());
        }
    }

    public interface OnRecipeListener {
        void onRecipeClick(int position);
    }
}
