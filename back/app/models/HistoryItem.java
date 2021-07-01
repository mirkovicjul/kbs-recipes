package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ebean.Model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "history")
public class HistoryItem extends Model {

    @Id
    @Column
    Long id;

    @Column
    @ManyToOne
    @JsonIgnore
    User user;

    @Column
    @ManyToOne
    Recipe recipe;

    @Column
    Long servings;

    @Column
    LocalDate date;

    public HistoryItem(User user, Recipe recipe, Long servings, LocalDate date) {
        this.user = user;
        this.recipe = recipe;
        this.servings = servings;
        this.date = date;
    }

    public HistoryItem(Long id, User user, Recipe recipe, Long servings, LocalDate date) {
        this.id = id;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
