package com.example.hello.hello.SearchRecipe;

public class Recipe {
    String recipe_name;
    private String recipe_image;
    private String recipe_calories;

    public Recipe(String recipe_name, String recipe_image ,String recipe_calories) {
        this.recipe_name = recipe_name;
        this.recipe_image = recipe_image;
        this.recipe_calories = recipe_calories;
    }

    public String getRecipe_name() {
        return recipe_name;
    }

    public String getRecipe_calories() { return recipe_calories; }

    public String getRecipe_image() {
        return recipe_image;
    }
}
