package drools.recommendation;

public class Ingredient {

    private Long id;
    private String name;
    private IngredientType type;
    private int fats;
    private int carbs;
    private int protein;

    public Ingredient(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Ingredient(Long id, String name, IngredientType type, int fats, int carbs, int protein) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.fats = fats;
        this.carbs = carbs;
        this.protein = protein;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFats() {
        return fats;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }
}
