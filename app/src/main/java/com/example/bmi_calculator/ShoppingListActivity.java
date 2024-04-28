package com.example.bmi_calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShoppingListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EditText editTextProductName;
    private Button buttonAddProduct;
    private ShoppingListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_list);

        recyclerView = findViewById(R.id.recyclerViewShoppingList);
        editTextProductName = findViewById(R.id.editTextProductName);
        buttonAddProduct = findViewById(R.id.buttonAddProduct);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ShoppingListAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

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
    }
}
