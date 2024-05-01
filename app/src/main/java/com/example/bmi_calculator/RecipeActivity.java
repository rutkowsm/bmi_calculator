package com.example.bmi_calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
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
        String genericSteps = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec at tortor nisi. Donec posuere nec nunc a ornare. Quisque pellentesque tempor nunc venenatis luctus. Aliquam mattis justo in turpis consectetur porttitor. Mauris mauris elit, efficitur id elementum vel, venenatis vitae felis. Nam iaculis vulputate sapien a porta. In turpis justo, auctor vel consectetur ut, lobortis nec est. Nunc dictum varius urna hendrerit fringilla. Aenean molestie eget metus id commodo.";

        List<String> kurczakIngredients = Arrays.asList(
                "600 g filetu z kurczaka",
                "2 łyżeczki przyprawy curry",
                "po 1/2 łyżeczki słodkiej papryki i kurkumy",
                "2 łyżki oliwy",
                "1/2 mniejszej cebuli",
                "1 mała marchewka",
                "1/2 papryki",
                "2 ząbki czosnku",
                "2 cm imbiru",
                "1 łyżka mąki",
                "150 ml bulionu (np. z kostki BIO)",
                "150 ml śmietanki 18% do zup i sosów lub mleka kokosowego"
        );
        List<String> makaronIngredients = Arrays.asList(
                "filety z piersi kurczaka 500 gramów",
                "Rosół z kury Knorr 1 sztuka",
                "makaron penne 300 gramów",
                "pieczarki 300 gramów",
                "cebula 1 sztuka",
                "Delikat przyprawa uniwersalna Knorr 1 łyżeczka",
                "woda 300 mililitrów",
                "śmietana 18% 200 mililitrów",
                "mąka pszenna 2 łyżki",
                "olej do smażenia 80 mililitrów",
                "mała natka pietruszki 1 pęczek",
                "starty parmezan lub inny żółty ser 100 gramów"
        );
        List<String> pulpetyIngredients = Arrays.asList(
                "500 g mielonego mięsa (z indyka, cielęcego lub wieprzowego np. z szynki)",
                "1 cebula",
                "2 ząbki czosnku",
                "1 łyżeczka suszonego oregano",
                "1/2 łyżeczki papryki w proszku",
                "3 łyżki bułki tartej lub ugotowanej kaszy jaglanej",
                "1 jajko",
                "1 łyżka sosu sojowego (opcjonalnie)",
                "125 ml bulionu drobiowego lub rosołu",
                "oliwa, masło do smażenia",
                "350 ml przecieru pomidorowego, passaty z butelki lub kartonu"
        );

        List<String> kanapkaIngredients = Arrays.asList(
                "Chleb oliwski",
                "Masło",
                "Ser",
                "Pomidor"
        );

        recipesList.add(new Recipe("Kurczak curry", "Pyszny kurczak curry z ryżem", R.drawable.kurczak, kurczakIngredients, genericSteps));
        recipesList.add(new Recipe("Makaron z kurczakiem", "Klasyczne spaghetti carbonara z boczkiem", R.drawable.makaron, makaronIngredients, genericSteps));
        recipesList.add(new Recipe("Pulpety w sosie pomidorowym", "Śródziemnomorskie pulpety z indyka w lekkim sosie", R.drawable.pulpety, pulpetyIngredients, genericSteps));
        recipesList.add(new Recipe("Kanapka Poncyliusza", "Pyszna kanapka autorska Pawła Poncyliusza", R.drawable.pulpety, kanapkaIngredients, genericSteps));

        adapter = new RecipeAdapter(this, recipesList, position -> {
            Recipe clickedRecipe = recipesList.get(position);

            Intent intent = new Intent(RecipeActivity.this, RecipeDetailActivity.class);
            intent.putExtra("recipeName", clickedRecipe.getName());
            intent.putExtra("recipeDescription", clickedRecipe.getDescription());
            intent.putExtra("recipeImageId", clickedRecipe.getImageResourceId());
            intent.putStringArrayListExtra("recipeIngredients", new ArrayList<>(clickedRecipe.getIngredients())); // Przekazanie listy składników
            intent.putExtra("recipeSteps", clickedRecipe.getSteps());
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);

        ImageView homeImg = findViewById(R.id.homeImg);
        homeImg.setOnClickListener(v -> {
            Intent intent = new Intent(RecipeActivity.this, SplashScreenActivity.class);
            startActivity(intent);
        });

        ImageView shoppingCartImg = findViewById(R.id.shoppingCartImg);
        shoppingCartImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecipeActivity.this, ShoppingListActivity.class);
                startActivity(intent);
            }
        });
    }
}
