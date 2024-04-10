package com.example.bmi_calculator;

public class Recipe {
    private String name;
    private String description;
    private int imageResourceId;

    public Recipe(String name, String description, int imageResourceId) {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    // Getters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getImageResourceId() { return imageResourceId; }
}


