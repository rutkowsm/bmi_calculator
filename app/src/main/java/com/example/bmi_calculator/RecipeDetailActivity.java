package com.example.bmi_calculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        // Initialize views
        ImageView recipeImageView = findViewById(R.id.recipeImage);
        TextView ingredientsTextView = findViewById(R.id.recipeIngredients);
        TextView nameTextView = findViewById(R.id.recipeName);
        TextView descriptionTextView = findViewById(R.id.recipeDescription);
        TextView stepsTextView = findViewById(R.id.recipeSteps);
        ImageView homeImg = findViewById(R.id.homeImg);
        ImageView shoppingCartImg = findViewById(R.id.shoppingCartImg);
        Button buttonAddIngredients = findViewById(R.id.buttonAddIngredients);

        // Retrieve data passed via Intent
        String recipeName = getIntent().getStringExtra("recipeName");
        String recipeDescription = getIntent().getStringExtra("recipeDescription");
        int recipeImageId = getIntent().getIntExtra("recipeImageId", 0);
        ArrayList<String> ingredients = getIntent().getStringArrayListExtra("recipeIngredients");
        String steps = getIntent().getStringExtra("recipeSteps");

        // Set data to views
        nameTextView.setText(recipeName);
        descriptionTextView.setText(recipeDescription);
        recipeImageView.setImageResource(recipeImageId);
        stepsTextView.setText(steps);
        ingredientsTextView.setText(TextUtils.join("\n- ", ingredients));

        homeImg.setOnClickListener(v -> {
            Intent intent = new Intent(RecipeDetailActivity.this, SplashScreenActivity.class);
            startActivity(intent);
        });


        shoppingCartImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecipeDetailActivity.this, ShoppingListActivity.class);
                startActivity(intent);
            }
        });

        buttonAddIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ingredients != null && !ingredients.isEmpty()) {
                    saveIngredientsToSharedPreferences(ingredients);
                    Toast.makeText(RecipeDetailActivity.this, "Ingredients have been added to the shopping list", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RecipeDetailActivity.this, "No ingredients to add", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void saveIngredientsToSharedPreferences(ArrayList<String> newIngredients) {
        SharedPreferences prefs = getSharedPreferences("ShoppingListPrefs", MODE_PRIVATE);
        Gson gson = new Gson();

        // Retrieve the existing shopping list
        String json = prefs.getString("shoppingList", null);
        Type type = new TypeToken<ArrayList<ShoppingProduct>>() {}.getType();
        List<ShoppingProduct> currentList = gson.fromJson(json, type);
        if (currentList == null) {
            currentList = new ArrayList<>();
        }

        // Add new ingredients to the list
        for (String ingredient : newIngredients) {
            currentList.add(new ShoppingProduct(ingredient, false));
        }

        // Save the updated list back to SharedPreferences
        SharedPreferences.Editor editor = prefs.edit();
        String updatedJson = gson.toJson(currentList);
        editor.putString("shoppingList", updatedJson);
        editor.apply();
    }



}


