package pl.arciemowicz.rankingbackend.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.arciemowicz.rankingbackend.service.exception.RateNotValidException;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

/**
 * Created by bartosz_arciemowicz on 13/03/2017.
 */
@Data
@NoArgsConstructor
public class Rate {

    String id;
    Type type;
    Double average;
    List<Integer> ratings = new ArrayList<>();

    public Rate(String id, Type type) {
        this.id = id;
        this.type = type;
    }

    public void addRating(Integer rating) {
        ratings.add(rating);
        average = updateAverage();
    }

    private Double updateAverage() {
        OptionalDouble newAverage = ratings.stream().mapToDouble(rating -> rating).average();
        if(newAverage.isPresent()) {
            return newAverage.getAsDouble();
        }
        else {
           throw new ArithmeticException("Couldn't properly count new average.");
        }
    }

    public void validate() throws RateNotValidException {
        if(id == null || id.isEmpty() || type == null) {
            throw new RateNotValidException();
        }
    }
}
