package pl.arciemowicz.rankingbackend.service;

import pl.arciemowicz.rankingbackend.domain.Rate;
import pl.arciemowicz.rankingbackend.domain.Type;
import pl.arciemowicz.rankingbackend.service.exception.RateNotValidException;
import pl.arciemowicz.rankingbackend.service.exception.RateNotFoundException;

import java.util.List;

/**
 * Created by bartosz_arciemowicz on 13/03/2017.
 */
public interface RatesService {
    Rate getRate(Type type, String id) throws RateNotFoundException;

    List<Rate> getRates(Type type);

    List<Rate> getRates(Type type, List<String> ids) throws RateNotFoundException;

    Rate addRating(Type type, String id, Integer rating) throws RateNotFoundException;

    Rate addRate(Rate rate) throws RateNotValidException;
}
