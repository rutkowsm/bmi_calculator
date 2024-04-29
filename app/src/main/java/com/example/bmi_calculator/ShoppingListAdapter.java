package com.example.bmi_calculator;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ShoppingViewHolder> {
    private List<ShoppingProduct> shoppingList;

    public ShoppingListAdapter() {
        this.shoppingList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ShoppingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_list_element_design, parent, false);
        return new ShoppingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingViewHolder holder, int position) {
        ShoppingProduct currentProduct = shoppingList.get(position);
        holder.textViewProductName.setText(currentProduct.getName());
        holder.checkBoxProductList.setChecked(currentProduct.isChecked());

        // Update text view appearance based on whether the checkbox is checked
        if (currentProduct.isChecked()) {
            holder.textViewProductName.setPaintFlags(holder.textViewProductName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.textViewProductName.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.gray_out));
        } else {
            holder.textViewProductName.setPaintFlags(holder.textViewProductName.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            holder.textViewProductName.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));  // Assume you have defined black color
        }

        // Set a listener for changes in checkbox state
        holder.checkBoxProductList.setOnCheckedChangeListener(null);
        holder.checkBoxProductList.setOnCheckedChangeListener((buttonView, isChecked) -> {
            currentProduct.setChecked(isChecked);
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return shoppingList.size();
    }

    public void addProduct(ShoppingProduct product) {
        shoppingList.add(product);
        notifyItemInserted(shoppingList.size() - 1);
    }

    public static class ShoppingViewHolder extends RecyclerView.ViewHolder {
        TextView textViewProductName;
        CheckBox checkBoxProductList;

        public ShoppingViewHolder(View itemView) {
            super(itemView);
            textViewProductName = itemView.findViewById(R.id.textViewProductName);
            checkBoxProductList = itemView.findViewById(R.id.checkBoxProductList);
        }
    }
}
