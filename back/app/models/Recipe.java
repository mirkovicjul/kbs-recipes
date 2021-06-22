package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @Column
    Long id;

    @Column
    String title;

    @Column
    String description;

    @Column
    Long numberOfPortions;

    @Column
    Boolean vegan;

    @Column
    Boolean vegetarian;

    @Column
    Boolean junkFood;

    @Column
    Long daysBeforeExpiration;

    public Recipe(Long id, String title, String description, Long numberOfPortions, Boolean vegan, Boolean vegetarian, Boolean junkFood, Long daysBeforeExpiration) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.numberOfPortions = numberOfPortions;
        this.vegan = vegan;
        this.vegetarian = vegetarian;
        this.junkFood = junkFood;
        this.daysBeforeExpiration = daysBeforeExpiration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getNumberOfPortions() {
        return numberOfPortions;
    }

    public void setNumberOfPortions(Long numberOfPortions) {
        this.numberOfPortions = numberOfPortions;
    }

    public Boolean getVegan() {
        return vegan;
    }

    public void setVegan(Boolean vegan) {
        this.vegan = vegan;
    }

    public Boolean getVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(Boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public Boolean getJunkFood() {
        return junkFood;
    }

    public void setJunkFood(Boolean junkFood) {
        this.junkFood = junkFood;
    }

    public Long getDaysBeforeExpiration() {
        return daysBeforeExpiration;
    }

    public void setDaysBeforeExpiration(Long daysBeforeExpiration) {
        this.daysBeforeExpiration = daysBeforeExpiration;
    }

}
