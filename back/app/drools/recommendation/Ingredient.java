package drools.recommendation;

public class Ingredient {

    private Long id;
    private String name;
    private IngredientType type;
    private Double fats;
    private Double carbs;
    private Double protein;

    public Ingredient(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Ingredient(Long id, String name, IngredientType type, Double fats, Double carbs, Double protein) {
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

    public Double getFats() {
        return fats;
    }

    public void setFats(Double fats) {
        this.fats = fats;
    }

    public Double getCarbs() {
        return carbs;
    }

    public void setCarbs(Double carbs) {
        this.carbs = carbs;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

}
