package models;

import io.ebean.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "ingredient_storage")
public class IngredientStorage extends Model {

    @Id
    @Column
    Long id;

    @Column
    @ManyToOne
    Ingredient ingredient;

    @Column
    @ManyToOne
    User user;

    @Column
    Double quantity;

    @Column
    @ManyToOne
    Measurement measurement;

    @Column
    LocalDate bestBefore;

    public IngredientStorage(Long id, Ingredient ingredient, Double quantity, Measurement measurement, LocalDate bestBefore) {
        this.id = id;
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.measurement = measurement;
        this.bestBefore = bestBefore;
    }

    public IngredientStorage(Ingredient ingredient, Double quantity, Measurement measurement, LocalDate bestBefore) {
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.measurement = measurement;
        this.bestBefore = bestBefore;
    }

    public IngredientStorage(Ingredient ingredient, User user, Double quantity, Measurement measurement, LocalDate bestBefore) {
        this.ingredient = ingredient;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
