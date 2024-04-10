package com.example.bmi_calculator;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        // Odbieranie danych przekazanych z intentu
        String recipeName = getIntent().getStringExtra("recipeName");
        String recipeDescription = getIntent().getStringExtra("recipeDescription");
        int recipeImageId = getIntent().getIntExtra("recipeImageId", 0);
        ArrayList<String> ingredients = getIntent().getStringArrayListExtra("recipeIngredients");
        String steps = getIntent().getStringExtra("recipeSteps");

        // Sprawdzenie, czy lista składników nie jest null i łączenie składników w jeden ciąg tekstowy
        String ingredientsText = (ingredients != null) ? TextUtils.join("\n", ingredients) : "Brak składników";

        // Znajdowanie widoków i ustawianie ich wartości
        TextView nameTextView = findViewById(R.id.recipeName);
        TextView descriptionTextView = findViewById(R.id.recipeDescription);
        ImageView recipeImageView = findViewById(R.id.recipeImage);
        TextView ingredientsTextView = findViewById(R.id.recipeIngredients);
        TextView stepsTextView = findViewById(R.id.recipeSteps);
        stepsTextView.setText(steps);

        nameTextView.setText(recipeName);
        descriptionTextView.setText(recipeDescription);
        recipeImageView.setImageResource(recipeImageId);
        ingredientsTextView.setText(ingredientsText);

        ImageView homeImg = findViewById(R.id.homeImg);
        homeImg.setOnClickListener(v -> {
            Intent intent = new Intent(RecipeDetailActivity.this, SplashScreenActivity.class);
            startActivity(intent);
        });
    }

}


