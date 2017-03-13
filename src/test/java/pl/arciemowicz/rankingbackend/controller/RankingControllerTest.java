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

        mvc.perform(MockMvcRequestBuilders.get("/movies/{movieId}/rate", sampleRate.getId()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(rateJson));
    }


}
