package pl.arciemowicz.rankingbackend.controller;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.arciemowicz.rankingbackend.service.RatesService;
import pl.arciemowicz.rankingbackend.domain.Rate;
import pl.arciemowicz.rankingbackend.domain.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by bartosz_arciemowicz on 13/03/2017.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RankingControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private RatesService ratesService;

    private Gson gson = new Gson();

    @Test
    public void getRate() throws Exception {
        String sampleId = "123";
        Double sampleAverage = 5.6;
        Rate sampleRate = new Rate(sampleId, Type.MOVIE);
        sampleRate.setAverage(sampleAverage);
        String rateJson = gson.toJson(sampleRate);

        when(ratesService.getRates(Type.MOVIE, sampleRate.getId())).thenReturn(sampleRate);

        mvc.perform(get("/rate/movie/{movieId}", sampleRate.getId()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(rateJson));
    }

    @Test
    public void getRates() throws Exception {
        String sampleId = "123";
        Double sampleAverage = 5.6;
        Rate sampleRate = new Rate(sampleId, Type.MOVIE);
        sampleRate.setAverage(sampleAverage);

        List<Rate> sampleRatesList = new ArrayList<>(Arrays.asList(sampleRate));
        String ratesListJson = gson.toJson(sampleRatesList);

        when(ratesService.getRates(Type.MOVIE)).thenReturn(sampleRatesList);

        mvc.perform(get("/rate/movie").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(ratesListJson));
    }

    @Test
    public void getRatesFilteredByIds() throws Exception {
        String sampleId = "123";
        Double sampleAverage = 5.6;
        Rate sampleRate = new Rate(sampleId, Type.MOVIE);
        sampleRate.setAverage(sampleAverage);

        List<Rate> sampleRatesList = new ArrayList<>(Arrays.asList(sampleRate));
        String ratesListJson = gson.toJson(sampleRatesList);

        List<String> sampleIds = new ArrayList<>(Arrays.asList(sampleId));
        String sampleIdsJson = gson.toJson(sampleIds);

        when(ratesService.getRates(Type.MOVIE, sampleIds)).thenReturn(sampleRatesList);

        mvc.perform(post("/rate/movie/filterIds")
                .contentType(MediaType.APPLICATION_JSON)
                .content(sampleIdsJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(ratesListJson));
    }

    @Test
    public void addRating() throws Exception {
        String sampleId = "123";
        Double sampleAverage = 5.;
        Double anotherRating = 10.;
        Rate sampleRate = new Rate(sampleId, Type.MOVIE);
        sampleRate.addRating(sampleAverage);
        sampleRate.addRating(anotherRating);
        sampleRate.setAverage(sampleAverage);

        String rateJson = gson.toJson(sampleRate);
        String anotherRatingJson = gson.toJson(anotherRating);

        when(ratesService.addRating(Type.MOVIE, sampleId, anotherRating)).thenReturn(sampleRate);

        mvc.perform(put("/rate/movie/{movieId}", sampleRate.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(anotherRatingJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(rateJson));
    }


}
