package pl.arciemowicz.rankingbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.arciemowicz.rankingbackend.domain.Rate;
import pl.arciemowicz.rankingbackend.domain.RateRepository;
import pl.arciemowicz.rankingbackend.domain.Type;
import pl.arciemowicz.rankingbackend.service.RatesService;
import pl.arciemowicz.rankingbackend.service.exception.RateNotValidException;
import pl.arciemowicz.rankingbackend.service.exception.RateNotFoundException;
import pl.arciemowicz.rankingbackend.service.exception.RatingValueOutOfAllowedRangeException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bartosz_arciemowicz on 14/03/2017.
 */
@Service
public class RatesServiceImpl implements RatesService {

    @Autowired
    RateRepository repository;

    @Value("${ranking.rating.min}")
    private Integer minRating;

    @Value("${ranking.rating.max}")
    private Integer maxRating;

    @Override
    public Rate getRate(Type type, String id) throws RateNotFoundException {
        Rate rate = repository.findByTypeAndId(type, id);
        if(rate == null) {
            throw new RateNotFoundException();
        }
        return rate;
    }

    @Override
    public List<Rate> getRates(Type type) {
        return repository.findAllByType(type);
    }

    @Override
    // Lambda cannot be smoothly implemented due to exception handling inside mapper
    public List<Rate> getRates(Type type, List<String> ids) throws RateNotFoundException {
        List<Rate> rates = new ArrayList<>();
        for(String id : ids) {
            rates.add(getRate(type, id));
        }
        return rates;
    }

    @Override
    public Rate addRating(Type type, String id, Integer rating) throws RateNotFoundException, RatingValueOutOfAllowedRangeException {
        if(rating < minRating || rating > maxRating) {
            throw new RatingValueOutOfAllowedRangeException();
        }
        Rate rate = getRate(type, id);
        rate.addRating(rating);
        return repository.save(rate);
    }

    @Override
    public Rate addRate(Rate rate) throws RateNotValidException {
        rate.validate();
        return repository.save(rate);
    }
}
