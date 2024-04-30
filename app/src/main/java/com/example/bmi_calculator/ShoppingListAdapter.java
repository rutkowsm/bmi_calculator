package com.example.bmi_calculator;

import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
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
        holder.checkBoxProductList.setOnCheckedChangeListener(null);  // Detach any existing listeners
        holder.checkBoxProductList.setChecked(currentProduct.isChecked());  // Set the initial state

        // Update the visual appearance based on the checked state
        updateTextAppearance(holder, currentProduct.isChecked());

        // Reattach the listener
        holder.checkBoxProductList.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (holder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                // Update the item's state
                ShoppingProduct product = shoppingList.get(holder.getAdapterPosition());
                product.setChecked(isChecked);
                updateTextAppearance(holder, isChecked);

                // Use a handler to delay notifying changes
                new Handler(Looper.getMainLooper()).post(() -> {
                    notifyItemChanged(holder.getAdapterPosition());
                });
            }
        });
    }

    private void updateTextAppearance(ShoppingViewHolder holder, boolean isChecked) {
        if (isChecked) {
            holder.textViewProductName.setPaintFlags(holder.textViewProductName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.textViewProductName.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.gray_out));
        } else {
            holder.textViewProductName.setPaintFlags(holder.textViewProductName.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            holder.textViewProductName.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
        }
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

    public void removeCheckedItems() {
        for (int i = shoppingList.size() - 1; i >= 0; i--) {  // Iterate backwards to avoid concurrent modification issues
            if (shoppingList.get(i).isChecked()) {
                shoppingList.remove(i);
                notifyItemRemoved(i);
            }
        }
    }

    public void clearShoppingList(){
        for (int i = shoppingList.size() - 1; i>= 0; i--) {
            shoppingList.remove(i);
            notifyItemRemoved(i);
        }
    }

    public List<ShoppingProduct> getShoppingList() {
        return shoppingList;
    }

    public void addProducts(List<ShoppingProduct> products) {
        int startSize = shoppingList.size();
        shoppingList.addAll(products);
        notifyItemRangeInserted(startSize, products.size());
    }

}
