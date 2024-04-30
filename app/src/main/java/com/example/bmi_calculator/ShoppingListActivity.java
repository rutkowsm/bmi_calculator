package com.example.bmi_calculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ShoppingListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EditText editTextProductName;
    private Button buttonAddProduct, buttonDeleteMarked, buttonClearList;
    private ShoppingListAdapter adapter;
    private ImageView homeImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_list);

        recyclerView = findViewById(R.id.recyclerViewShoppingList);
        editTextProductName = findViewById(R.id.editTextProductName);
        buttonAddProduct = findViewById(R.id.buttonAddProduct);
        buttonDeleteMarked = findViewById(R.id.buttonDeleteMarked);
        buttonClearList = findViewById(R.id.buttonClearList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        homeImg = findViewById(R.id.homeImg);

        adapter = new ShoppingListAdapter();
        recyclerView.setAdapter(adapter);

        restoreShoppingList();

        buttonAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = editTextProductName.getText().toString();
                if (!productName.isEmpty()) {
                    adapter.addProduct(new ShoppingProduct(productName, false));
                    editTextProductName.setText("");
                }
            }
        });

        buttonDeleteMarked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.removeCheckedItems();
                    }
                });
            }
        });

        buttonClearList.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.clearShoppingList();
                    }
                });
            }
        }));

        homeImg.setOnClickListener(v -> {
            Intent intent = new Intent(ShoppingListActivity.this, SplashScreenActivity.class);
            startActivity(intent);
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        saveShoppingList();  // Save items when the activity is paused
    }

    private void saveShoppingList() {
        SharedPreferences prefs = getSharedPreferences("ShoppingListPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(adapter.getShoppingList());
        editor.putString("shoppingList", json);
        editor.apply();
    }

    private void restoreShoppingList() {
        SharedPreferences prefs = getSharedPreferences("ShoppingListPrefs", MODE_PRIVATE);
        String json = prefs.getString("shoppingList", null);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ShoppingProduct>>() {}.getType();
        List<ShoppingProduct> items = gson.fromJson(json, type);
        if (items != null) {
            for (ShoppingProduct product : items) {
                adapter.addProduct(product);
            }
        }
    }
}
