package pl.arciemowicz.rankingbackend.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by bartosz_arciemowicz on 14/03/2017.
 */
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Given rate structure is not valid")
public class RateNotValidException extends Exception {

    public RateNotValidException() {
        super("Given rate structure is not valid");
    }
}
