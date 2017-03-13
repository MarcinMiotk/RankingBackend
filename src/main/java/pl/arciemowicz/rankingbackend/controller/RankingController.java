package pl.arciemowicz.rankingbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.arciemowicz.rankingbackend.service.RatesService;
import pl.arciemowicz.rankingbackend.domain.Rate;
import pl.arciemowicz.rankingbackend.domain.Type;

/**
 * Created by bartosz_arciemowicz on 13/03/2017.
 */
@RestController("/")
public class RankingController {

    @Autowired
    RatesService ratesService;

    @RequestMapping(value = "movies/{movieId}/rate", method = RequestMethod.GET)
    public Rate getMovieRate(@PathVariable("movieId") String movieId) {
        return ratesService.getRate(Type.MOVIE, movieId);
    }
}
