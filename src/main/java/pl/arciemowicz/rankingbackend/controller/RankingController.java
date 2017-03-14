package pl.arciemowicz.rankingbackend.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.arciemowicz.rankingbackend.service.RatesService;
import pl.arciemowicz.rankingbackend.domain.Rate;
import pl.arciemowicz.rankingbackend.domain.Type;
import pl.arciemowicz.rankingbackend.service.exception.RateNotValidException;
import pl.arciemowicz.rankingbackend.service.exception.RateNotFoundException;
import pl.arciemowicz.rankingbackend.service.exception.RatingValueOutOfAllowedRangeException;

import java.util.List;

/**
 * Created by bartosz_arciemowicz on 13/03/2017.
 */
@RestController
@RequestMapping("rate")
public class RankingController {

    @Autowired
    RatesService ratesService;

    @ApiOperation(value = "Get Rate-Movie object (containing movieId, average rating and ratings) with given movieId", nickname = "getMovieRate")
    @RequestMapping(value = "/movie/{movieId}", method = RequestMethod.GET)
    public Rate getMovieRate(@PathVariable("movieId") String movieId) throws RateNotFoundException {
        return ratesService.getRate(Type.MOVIE, movieId);
    }

    @ApiOperation(value = "Get list of all Rate-Movie objects (containing movieId, average rating and ratings)", nickname = "getMoviesRates")
    @RequestMapping(value = "/movie", method = RequestMethod.GET)
    public List<Rate> getMoviesRates() {
        return ratesService.getRates(Type.MOVIE);
    }

    @ApiOperation(value = "Get list of Rate-Movie objects (containing movieId, average rating and ratings) with ids from given list", nickname = "getMoviesRatesFilteredIds")
    @RequestMapping(value = "/movie/filterIds", method = RequestMethod.POST)
    public List<Rate> getMoviesRatesFilteredIds(@RequestBody List<String> ids) throws RateNotFoundException {
        return ratesService.getRates(Type.MOVIE, ids);
    }

    @ApiOperation(value = "Put a rating (one vote) to a Rate-Movie object with given movieId", nickname = "addMovieRating")
    @RequestMapping(value = "/movie/{movieId}", method = RequestMethod.PUT)
    public Rate addMovieRating(@PathVariable("movieId") String movieId, @RequestBody Integer rate) throws RateNotFoundException, RatingValueOutOfAllowedRangeException {
        return ratesService.addRating(Type.MOVIE, movieId, rate);
    }

    @ApiOperation(value = "Post new Rate-Movie object – providing movieId is necessary to properly process request.", nickname = "addMovieRate")
    @RequestMapping(value = "/movie", method = RequestMethod.POST)
    public Rate addMovieRate(@RequestBody Rate rate) throws RateNotValidException {
        rate.setType(Type.MOVIE);
        return ratesService.addRate(rate);
    }
}