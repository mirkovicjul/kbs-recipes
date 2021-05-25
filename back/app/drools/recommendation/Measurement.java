package drools.recommendation;

public class Measurement {

    private String name;

    private Double proportion;

    public Measurement(String name) {
        this.name = name;
    }

    public Measurement(String name, Double proportion) {
        this.name = name;
        this.proportion = proportion;
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
