package drools.recommendation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Recipe {

    private long id;

    private String name;

    private List<RecipeIngredient> ingredients;

    private Boolean vegan;

    private Boolean vegetarian;

    private Boolean junkFood;

    private int servings = 1;

    public Recipe(long id, String name, List<RecipeIngredient> ingredients) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
    }

    public Recipe(long id, String name, List<RecipeIngredient> ingredients, Boolean vegan, Boolean vegetarian, Boolean junkFood, int servings) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.vegan = vegan;
        this.vegetarian = vegetarian;
        this.junkFood = junkFood;
        this.servings = servings;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RecipeIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipeIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Boolean getVegan() {
        return vegan;
    }

    public void setVegan(Boolean vegan) {
        this.vegan = vegan;
    }

    public Boolean getVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(Boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public Boolean getJunkFood() {
        return junkFood;
    }

    public void setJunkFood(Boolean junkFood) {
        this.junkFood = junkFood;
    }
}
