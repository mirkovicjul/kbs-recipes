package drools.recommendation;

import java.util.List;

public class Recipe {

    private long id;

    private String name;

    private List<Ingredient> ingredients;

    public Recipe(long id, String name, List<Ingredient> ingredients) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

}
