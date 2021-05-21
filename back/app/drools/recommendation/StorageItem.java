package drools.recommendation;


import java.time.LocalDate;

public class StorageItem {

    private Ingredient ingredient;

    private Quantity quantity;

    private LocalDate bestBefore;

    public StorageItem(Ingredient ingredient, Quantity quantity, LocalDate bestBefore) {
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.bestBefore = bestBefore;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    public LocalDate getBestBefore() {
        return bestBefore;
    }

    public void setBestBefore(LocalDate bestBefore) {
        this.bestBefore = bestBefore;
    }
}
