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

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        assertThat(result).isEqualsToByComparingFields(sampleRate);

        repository.delete(sampleRate);
    }

    @Test
    public void getRates() throws Exception {
        String sampleId = "123";
        String sampleId2 = "456";
        Double sampleAverage = 5.6;
        Rate sampleRate = new Rate(sampleId, Type.MOVIE);
        Rate sampleRate2 = new Rate(sampleId2, Type.MOVIE);
        sampleRate.setAverage(sampleAverage);
        sampleRate2.setAverage(sampleAverage);
        repository.save(sampleRate);
        repository.save(sampleRate2);
        List<Rate> ratesList = new ArrayList<>(Arrays.asList(sampleRate, sampleRate2));

        List<Rate> result = ratesService.getRates(Type.MOVIE);

        assertThat(result).containsAll(ratesList);

        repository.delete(sampleRate);
        repository.delete(sampleRate2);
    }

}
