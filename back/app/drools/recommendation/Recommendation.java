package drools.recommendation;

public class Recommendation {

    private long recipeId;

    private int hit;

    public Recommendation(long recipeId, int hit) {
        this.recipeId = recipeId;
        this.hit = hit;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

}
