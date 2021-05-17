package drools.recommendation;

public class Ingredient {

    private Long id;
    private String name;
    private String type;

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

    public String getType() {
        return type;
    }

}
