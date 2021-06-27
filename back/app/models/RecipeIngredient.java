package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ebean.Model;

import javax.persistence.*;

@Entity
@Table(name = "recipe_ingredients")
public class RecipeIngredient extends Model {

    @Id
    @Column
    Long id;

    @Column
    @ManyToOne
    Ingredient ingredient;

    @Column
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    @JsonIgnore
    Recipe recipe;

    @Column
    @ManyToOne
    Measurement measurement;

    @Column
    Double quantity;

    public RecipeIngredient(Long id, Ingredient ingredient, Recipe recipe, Measurement measurement, Double quantity) {
        this.id = id;
        this.ingredient = ingredient;
        this.recipe = recipe;
        this.measurement = measurement;
        this.quantity = quantity;
    }

    public RecipeIngredient(Ingredient ingredient, Recipe recipe, Measurement measurement, Double quantity) {
        this.ingredient = ingredient;
        this.recipe = recipe;
        this.measurement = measurement;
        this.quantity = quantity;
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

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
