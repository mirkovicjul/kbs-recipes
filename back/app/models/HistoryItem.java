package models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "history")
public class HistoryItem {

    @Id
    @Column
    Long id;

    @Column
    @ManyToOne
    Recipe recipe;

    @Column
    Long servings;

    @Column
    LocalDate date;

    public HistoryItem(Long id, Recipe recipe, Long servings, LocalDate date) {
        this.id = id;
        this.recipe = recipe;
        this.servings = servings;
        this.date = date;
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

    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) { this.date = date; }
}
