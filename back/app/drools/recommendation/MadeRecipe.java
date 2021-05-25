package drools.recommendation;

import java.time.LocalDate;

public class MadeRecipe {

    private Recipe recipe;

    private LocalDate madeDate;

    private int servings;

    public MadeRecipe(Recipe recipe, LocalDate madeDate, int servings) {
        this.recipe = recipe;
        this.madeDate = madeDate;
        this.servings = servings;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public LocalDate getMadeDate() {
        return madeDate;
    }

    public void setMadeDate(LocalDate madeDate) {
        this.madeDate = madeDate;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }
}
