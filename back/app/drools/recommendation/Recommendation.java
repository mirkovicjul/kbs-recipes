package drools.recommendation;

import java.util.List;

public class Recommendation {

    private long recipeId;

    private int hit;

    private List<String> messages;

    public Recommendation(long recipeId, int hit) {
        this.recipeId = recipeId;
        this.hit = hit;
    }

    public Recommendation(long recipeId, int hit, List<String> messages) {
        this.recipeId = recipeId;
        this.hit = hit;
        this.messages = messages;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
