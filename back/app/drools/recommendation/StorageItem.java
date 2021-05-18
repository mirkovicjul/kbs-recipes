package drools.recommendation;

import org.joda.time.DateTime;

public class StorageItem {

    private Ingredient ingredient;

    private Quantity quantity;

    private DateTime bestBefore;

    public StorageItem(Ingredient ingredient, Quantity quantity, DateTime bestBefore) {
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

    public DateTime getBestBefore() {
        return bestBefore;
    }

    public void setBestBefore(DateTime bestBefore) {
        this.bestBefore = bestBefore;
    }
}
