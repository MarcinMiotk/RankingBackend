package pl.arciemowicz.rankingbackend.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by bartosz_arciemowicz on 14/03/2017.
 */
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Given rating value is out of allowed range")
public class RatingValueOutOfAllowedRangeException extends Exception {

    public RatingValueOutOfAllowedRangeException() {
        super("Given rating value is out of allowed range");
    }
}
