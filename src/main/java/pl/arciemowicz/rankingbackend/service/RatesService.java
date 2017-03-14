package pl.arciemowicz.rankingbackend.service;

import pl.arciemowicz.rankingbackend.domain.Rate;
import pl.arciemowicz.rankingbackend.domain.Type;

import java.util.List;

/**
 * Created by bartosz_arciemowicz on 13/03/2017.
 */
public interface RatesService {
    Rate getRates(Type type, String movieId);

    List<Rate> getRates(Type type);

    List<Rate> getRates(Type type, List<String> ids);

    Rate addRating(Type movie, String movieId, Double rate);
}
