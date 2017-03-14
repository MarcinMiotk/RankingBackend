package pl.arciemowicz.rankingbackend.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by bartosz_arciemowicz on 14/03/2017.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Rate Not Found")
public class RateNotFoundException extends Exception {

    public RateNotFoundException() {
        super("Rate Not Found");
    }
}
