package drools.recommendation;

public class Measurement {

    private Long id;

    private String name;

    private Double proportion;

    public Measurement(String name, Double proportion) {
        this.name = name;
        this.proportion = proportion;
    }

    public Measurement(Long id, String name, Double proportion) {
        this.id = id;
        this.name = name;
        this.proportion = proportion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getProportion() {
        return proportion;
    }

    public void setProportion(Double proportion) {
        this.proportion = proportion;
    }
}
