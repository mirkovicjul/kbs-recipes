package drools.recommendation;

import java.util.Map;

public class Recommendation {

    private Long recipeId;

    private Double hit;

    public Recommendation(Long recipeId, Double hit) {
        this.recipeId = recipeId;
        this.hit = hit;
    }

}
