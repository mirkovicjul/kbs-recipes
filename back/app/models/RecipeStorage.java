package models;

import io.ebean.Model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "recipe_storage")
public class RecipeStorage extends Model {

    @Id
    @Column
    Long id;

    @Column
    @ManyToOne
    Recipe recipe;

    @Column
    Long servings;

    @Column
    LocalDate bestBefore;

    public RecipeStorage(Long id, Recipe recipe, Long servings, LocalDate bestBefore) {
        this.id = id;
        this.recipe = recipe;
        this.servings = servings;
        this.bestBefore = bestBefore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Long getServings() {
        return servings;
    }

    public void setServings(Long servings) {
        this.servings = servings;
    }

    public LocalDate getBestBefore() {
        return bestBefore;
    }

    public void setBestBefore(LocalDate bestBefore) {
        this.bestBefore = bestBefore;
    }

}
