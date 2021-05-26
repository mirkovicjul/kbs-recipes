package drools.recommendation;

public class RecipeIngredient {

    private Ingredient ingredient;

    private Double amount;

    private Measurement unit;

    public RecipeIngredient(Ingredient ingredient, Double amount, Measurement unit) {
        this.ingredient = ingredient;
        this.amount = amount;
        this.unit = unit;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Measurement getUnit() {
        return unit;
    }

    public void setUnit(Measurement unit) {
        this.unit = unit;
    }
}
