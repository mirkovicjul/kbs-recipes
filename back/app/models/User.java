package models;

import io.ebean.Model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends Model {

    @Id
    @Column
    Long id;

    @Column
    String username;

    @Column
    String email;

    @Column
    String password;

    @Column
    Long userType;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_likes",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "ingredient_id") }
    )
    Set<Ingredient> likes;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_dislikes",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "ingredient_id") }
    )
    Set<Ingredient> dislikes;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_allergies",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "ingredient_id") }
    )
    Set<Ingredient> allergies;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_unavailable_ingredients",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "ingredient_id") }
    )
    Set<Ingredient> unavailable;

    public User(String username, String email, String password, Long userType) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public User(Long id, String username, String email, String password, Long userType, Set<Ingredient> likes, Set<Ingredient> dislikes, Set<Ingredient> allergies, Set<Ingredient> unavailable) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.likes = likes;
        this.dislikes = dislikes;
        this.allergies = allergies;
        this.unavailable = unavailable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUserType() {
        return userType;
    }

    public void setUserType(Long userType) {
        this.userType = userType;
    }

    public Set<Ingredient> getLikes() {
        return likes;
    }

    public void setLikes(Set<Ingredient> likes) {
        this.likes = likes;
    }

    public Set<Ingredient> getDislikes() {
        return dislikes;
    }

    public void setDislikes(Set<Ingredient> dislikes) {
        this.dislikes = dislikes;
    }

    public Set<Ingredient> getAllergies() {
        return allergies;
    }

    public void setAllergies(Set<Ingredient> allergies) {
        this.allergies = allergies;
    }

    public Set<Ingredient> getUnavailable() {
        return unavailable;
    }

    public void setUnavailable(Set<Ingredient> unavailable) {
        this.unavailable = unavailable;
    }
}
