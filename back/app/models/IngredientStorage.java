package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "ingredient_storage")
public class IngredientStorage {

    @Id
    @Column
    Long id;

    @Column
    User user;

    @Column
    Ingredient ingredient;

    @Column
    Double quantity;

    @Column
    Measurement measurement;

    @Column
    LocalDate bestBefore;

    public IngredientStorage(Long id, User user, Ingredient ingredient, Double quantity, Measurement measurement, LocalDate bestBefore) {
        this.id = id;
        this.user = user;
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.measurement = measurement;
        this.bestBefore = bestBefore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    public LocalDate getBestBefore() {
        return bestBefore;
    }

    public void setBestBefore(LocalDate bestBefore) {
        this.bestBefore = bestBefore;
    }

}
