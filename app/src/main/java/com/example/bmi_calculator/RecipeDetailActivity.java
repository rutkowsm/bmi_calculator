package com.example.bmi_calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        String recipeName = getIntent().getStringExtra("recipeName");
        String recipeDescription = getIntent().getStringExtra("recipeDescription");
        int recipeImageId = getIntent().getIntExtra("recipeImageId", 0);

        TextView nameTextView = findViewById(R.id.recipeName);
        TextView descriptionTextView = findViewById(R.id.recipeDescription);
        ImageView recipeImageView = findViewById(R.id.recipeImage);

        nameTextView.setText(recipeName);
        descriptionTextView.setText(recipeDescription);
        recipeImageView.setImageResource(recipeImageId);

        ImageView homeImg = findViewById(R.id.homeImg);
        homeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecipeDetailActivity.this, RecipeActivity.class);
                startActivity(intent);
            }
        });
    }
}

