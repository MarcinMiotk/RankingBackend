package pl.arciemowicz.rankingbackend.controller;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
import java.util.List;

import static org.mockito.Mockito.*;
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
        Double sampleValue = 5.6;
        Rate sampleRate = new Rate(sampleId, Type.MOVIE, sampleValue);
        String rateJson = gson.toJson(sampleRate);

        when(ratesService.getRate(Type.MOVIE, sampleRate.getId())).thenReturn(sampleRate);

        mvc.perform(MockMvcRequestBuilders.get("/rate/movie/{movieId}", sampleRate.getId()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(rateJson));
    }

    @Test
    public void getRates() throws Exception {
        String sampleId = "123";
        Double sampleValue = 5.6;
        List<Rate> sampleRatesList = new ArrayList<>();
        sampleRatesList.add(new Rate(sampleId, Type.MOVIE, sampleValue));
        String ratesListJson = gson.toJson(sampleRatesList);

        when(ratesService.getRate(Type.MOVIE)).thenReturn(sampleRatesList);

        mvc.perform(MockMvcRequestBuilders.get("/rate/movie").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(ratesListJson));
    }


}
