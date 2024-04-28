package com.example.bmi_calculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ShoppingListHolder> {
    private List<ShoppingProduct> productList;
    public ShoppingListAdapter(List<ShoppingProduct> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ShoppingListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_list_element_design, parent, false);
        return new ShoppingListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingListHolder holder, int position) {
        ShoppingProduct product = productList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void addProduct(ShoppingProduct product) {
        productList.add(product);
        notifyDataSetChanged();
    }

    public class ShoppingListHolder extends RecyclerView.ViewHolder {
        private TextView productNameTextView;
        private CheckBox productCheckBox;

        public ShoppingListHolder(@NonNull View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.textViewProductName);
            productCheckBox = itemView.findViewById(R.id.checkBoxProductList);

            productCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    ShoppingProduct product = productList.get(position);
                    product.setChecked(isChecked);
                }
            });
        }

        public void bind(ShoppingProduct product) {
            productNameTextView.setText(product.getName());
            productCheckBox.setChecked(product.isChecked());
        }
    }
}
