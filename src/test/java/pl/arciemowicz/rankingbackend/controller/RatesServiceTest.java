package pl.arciemowicz.rankingbackend.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.arciemowicz.rankingbackend.domain.Rate;
import pl.arciemowicz.rankingbackend.domain.RateRepository;
import pl.arciemowicz.rankingbackend.domain.Type;
import pl.arciemowicz.rankingbackend.service.RatesService;

/**
 * Created by bartosz_arciemowicz on 13/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RatesServiceTest {

    @Autowired
    private RatesService ratesService;

    @Autowired
    RateRepository repository;

    @Test
    public void getRate() throws Exception {
        String sampleId = "123";
        Double sampleAverage = 5.6;
        Rate sampleRate = new Rate(sampleId, Type.MOVIE);
        sampleRate.setAverage(sampleAverage);
        repository.save(sampleRate);

        Rate result = ratesService.getRate(Type.MOVIE, sampleId);

        Assert.assertEquals(sampleRate, result);

        repository.delete(sampleRate);
    }

}
