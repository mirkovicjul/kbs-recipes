package drools.recommendation;

import java.util.List;

public class User {

    private Long id;

    private List<Ingredient> likes;

    private List<Ingredient> dislikes;

    private List<Ingredient> allergies;

    private List<Ingredient> unavailable;

    private List<StorageItem> storage;

    private List<MadeRecipe> history;

    public User(
            Long id,
            List<Ingredient> likes,
            List<Ingredient> allergies,
            List<Ingredient> dislikes,
            List<Ingredient> unavailable,
            List<StorageItem> storage
    ) {
        this.id = id;
        this.likes = likes;
        this.allergies = allergies;
        this.dislikes = dislikes;
        this.unavailable = unavailable;
        this.storage = storage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Ingredient> getLikes() {
        return likes;
    }

    public void setLikes(List<Ingredient> likes) {
        this.likes = likes;
    }

    public List<Ingredient> getDislikes() {
        return dislikes;
    }

    public void setDislikes(List<Ingredient> dislikes) {
        this.dislikes = dislikes;
    }

    public List<Ingredient> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<Ingredient> allergies) {
        this.allergies = allergies;
    }

    public List<Ingredient> getUnavailable() {
        return unavailable;
    }

    public void setUnavailable(List<Ingredient> unavailable) {
        this.unavailable = unavailable;
    }

    public List<StorageItem> getStorage() {
        return storage;
    }

    public void setStorage(List<StorageItem> storage) {
        this.storage = storage;
    }

    public List<MadeRecipe> getHistory() {
        return history;
    }

    public void setHistory(List<MadeRecipe> history) {
        this.history = history;
    }
}
