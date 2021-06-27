package models;

import io.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "measurements")
public class Measurement extends Model {

    @Id
    @Column
    Long id;

    @Column
    String measurement;

    @Column
    Double proportion;

    public Measurement(Long id, String measurement, Double proportion) {
        this.id = id;
        this.measurement = measurement;
        this.proportion = proportion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public Double getProportion() {
        return proportion;
    }

    public void setProportion(Double proportion) {
        this.proportion = proportion;
    }

}
