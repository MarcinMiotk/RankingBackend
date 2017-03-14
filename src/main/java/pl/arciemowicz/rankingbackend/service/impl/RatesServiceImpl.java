package pl.arciemowicz.rankingbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arciemowicz.rankingbackend.domain.Rate;
import pl.arciemowicz.rankingbackend.domain.RateRepository;
import pl.arciemowicz.rankingbackend.domain.Type;
import pl.arciemowicz.rankingbackend.service.RatesService;

import java.util.List;

/**
 * Created by bartosz_arciemowicz on 14/03/2017.
 */
@Service
public class RatesServiceImpl implements RatesService {

    @Autowired
    RateRepository repository;

    @Override
    public Rate getRate(Type type, String id) {
        return repository.findByTypeAndId(type, id);
    }

    @Override
    public List<Rate> getRates(Type type) {
        return null;
    }

    @Override
    public List<Rate> getRates(Type type, List<String> ids) {
        return null;
    }

    @Override
    public Rate addRating(Type movie, String movieId, Double rate) {
        return null;
    }

    @Override
    public Rate addRate(Rate rate) {
        return null;
    }
}
