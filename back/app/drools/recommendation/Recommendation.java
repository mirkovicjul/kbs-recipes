package drools.recommendation;

public class Recommendation {

    private int recipeId;

    private Double hit;

    public Recommendation(int recipeId, Double hit) {
        this.recipeId = recipeId;
        this.hit = hit;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public Double getHit() {
        return hit;
    }

}
