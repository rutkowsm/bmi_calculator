package com.example.bmi_calculator;

import java.util.List;

public class Recipe {
    private String name;
    private String description;
    private int imageResourceId;
    private List<String> ingredients;
    private String steps;

    // Zaktualizowany konstruktor
    public Recipe(String name, String description, int imageResourceId, List<String> ingredients, String steps) {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    // Getters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getImageResourceId() { return imageResourceId; }
    public List<String> getIngredients() { return ingredients; } // Nowy getter
    public String getSteps() { return steps; } // Nowy getter
}
