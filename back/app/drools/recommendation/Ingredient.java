package drools.recommendation;

public class Ingredient {

    private Long id;
    private String name;
    private IngredientType type;
    private int fats;
    private int carbs;
    private int protein;
    private int calories;

    public Ingredient(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public IngredientType getType() {
        return type;
    }

    public void setType(IngredientType type) {
        this.type = type;
    }
}
