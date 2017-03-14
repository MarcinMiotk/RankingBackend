package pl.arciemowicz.rankingbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.arciemowicz.rankingbackend.service.RatesService;
import pl.arciemowicz.rankingbackend.domain.Rate;
import pl.arciemowicz.rankingbackend.domain.Type;

import java.util.List;

/**
 * Created by bartosz_arciemowicz on 13/03/2017.
 */
@RestController("/")
public class RankingController {

    @Autowired
    RatesService ratesService;

    @RequestMapping(value = "rate/movie/{movieId}", method = RequestMethod.GET)
    public Rate getMovieRate(@PathVariable("movieId") String movieId) {
        return ratesService.getRates(Type.MOVIE, movieId);
    }

    @RequestMapping(value = "rate/movie", method = RequestMethod.GET)
    public List<Rate> getMoviesRates() {
        return ratesService.getRates(Type.MOVIE);
    }

    @RequestMapping(value = "rate/movie/filterIds", method = RequestMethod.POST)
    public List<Rate> getMoviesRatesFilteredIds(@RequestBody List<String> ids) {
        return ratesService.getRates(Type.MOVIE, ids);
    }

    @RequestMapping(value = "rate/movie/{movieId}", method = RequestMethod.PUT)
    public Rate addMovieRating(@PathVariable("movieId") String movieId, @RequestBody Double rate) {
        return ratesService.addRating(Type.MOVIE, movieId, rate);
    }
}
