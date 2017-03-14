package pl.arciemowicz.rankingbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arciemowicz.rankingbackend.domain.Rate;
import pl.arciemowicz.rankingbackend.domain.RateRepository;
import pl.arciemowicz.rankingbackend.domain.Type;
import pl.arciemowicz.rankingbackend.service.RatesService;

import java.util.List;
import java.util.stream.Collectors;

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
        return repository.findAllByType(type);
    }

    @Override
    public List<Rate> getRates(Type type, List<String> ids) {
        return ids.stream().map(id -> getRate(type, id)).collect(Collectors.toList());
    }

    @Override
    public Rate addRating(Type type, String id, Integer rating) {
        Rate rate = getRate(type, id);
        rate.addRating(rating);
        return repository.save(rate);
    }

    @Override
    public Rate addRate(Rate rate) {
        return null;
    }
}
