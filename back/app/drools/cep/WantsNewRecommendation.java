package drools.cep;

import java.time.LocalDate;

public class WantsNewRecommendation {

    private LocalDate timestamp;

    public WantsNewRecommendation() {
        this.timestamp = LocalDate.now();
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

}
