package pl.arciemowicz.rankingbackend.service;

import pl.arciemowicz.rankingbackend.domain.Rate;
import pl.arciemowicz.rankingbackend.domain.Type;

import java.util.List;

/**
 * Created by bartosz_arciemowicz on 13/03/2017.
 */
public interface RatesService {
    Rate getRate(Type type, String movieId);

    List<Rate> getRate(Type type);
}
