package models;

import drools.recommendation.IngredientType;

import javax.persistence.*;

@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @Column
    Long id;

    @Column
    String ingredient;

    @Column
    Double fats;

    @Column
    Double protein;

    @Column
    Double carbs;

    @Column
    @Enumerated(EnumType.STRING)
    IngredientType type;

    public Ingredient(Long id, String ingredient, Double fats, Double protein, Double carbs, IngredientType type) {
        this.id = id;
        this.ingredient = ingredient;
        this.fats = fats;
        this.protein = protein;
        this.carbs = carbs;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public Double getFats() {
        return fats;
    }

    public void setFats(Double fats) {
        this.fats = fats;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public Double getCarbs() {
        return carbs;
    }

    public void setCarbs(Double carbs) {
        this.carbs = carbs;
    }

    public IngredientType getType() {
        return type;
    }

    public void setType(IngredientType type) {
        this.type = type;
    }

}
