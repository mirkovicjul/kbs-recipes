package drools.recommendation;


import java.time.LocalDate;

public class StorageItem {

    private Ingredient ingredient;

    private Double amount;

    private Measurement unit;

    private LocalDate bestBefore;

    public StorageItem(Ingredient ingredient, Double amount, Measurement unit, LocalDate bestBefore) {
        this.ingredient = ingredient;
        this.amount = amount;
        this.unit = unit;
        this.bestBefore = bestBefore;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public LocalDate getBestBefore() {
        return bestBefore;
    }

    public void setBestBefore(LocalDate bestBefore) {
        this.bestBefore = bestBefore;
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
