package drools.recommendation;

public class Quantity {

    private Double amount;

    private Measurement unit;

    public Quantity(final Double amount, Measurement unit) {
        this.amount = amount;
        this.unit = unit;
    }

    public Double getGrams() {
        return this.amount * unit.getProportion();
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Measurement getUnit() {
        return unit;
    }

    public void setUnit(Measurement unit) {
        this.unit = unit;
    }

}
