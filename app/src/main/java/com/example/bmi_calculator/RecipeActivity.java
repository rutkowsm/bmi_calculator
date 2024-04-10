package com.example.bmi_calculator;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class RecipeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecipeAdapter adapter;
    private List<Recipe> recipesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipies);

        recyclerView = findViewById(R.id.recycler_view_recipies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicjalizacja listy przepisów
        recipesList = new ArrayList<>();
        recipesList.add(new Recipe("Kurczak curry", "Pyszny kurczak curry z ryżem", R.drawable.kurczak));
        recipesList.add(new Recipe("Makaron z kurczakiem", "Klasyczne spaghetti carbonara z boczkiem", R.drawable.makaron));
        recipesList.add(new Recipe("Pulpety w sosie pomidorowym", "Śródziemnomorskie pulpety z indyka w lekkim sosie", R.drawable.pulpety));

        adapter = new RecipeAdapter(this, recipesList, position -> {
            Recipe clickedRecipe = recipesList.get(position);

            Intent intent = new Intent(RecipeActivity.this, RecipeDetailActivity.class);
            intent.putExtra("recipeName", clickedRecipe.getName());
            intent.putExtra("recipeDescription", clickedRecipe.getDescription());
            intent.putExtra("recipeImageId", clickedRecipe.getImageResourceId());
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
    }
}
