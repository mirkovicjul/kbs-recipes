package drools.recommendation;

import java.util.List;

public class User {

    private Long id;
    private List<Ingredient> likes;
    private List<Ingredient> dislikes;
    private List<Ingredient> allergies;

    public User(Long id, List<Ingredient> likes, List<Ingredient> allergies, List<Ingredient> dislikes) {
        this.id = id;
        this.likes = likes;
        this.allergies = allergies;
        this.dislikes = dislikes;
    }

    public Long getId() {
        return id;
    }

    public List<Ingredient> getLikes() {
        return likes;
    }

    public List<Ingredient> getAllergies() {
        return allergies;
    }

    public List<Ingredient> getDislikes() { return dislikes; }

}
