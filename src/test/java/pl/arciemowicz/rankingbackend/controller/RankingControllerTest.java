package pl.arciemowicz.rankingbackend.controller;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.arciemowicz.rankingbackend.domain.Rate;
import pl.arciemowicz.rankingbackend.domain.Type;
import pl.arciemowicz.rankingbackend.service.RatesService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by bartosz_arciemowicz on 13/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RankingControllerTest {

    @Autowired
    private RatesService ratesService;

    @Autowired
    private MockMvc mvc;

    private Gson gson = new Gson();

    @Value("${ranking.rating.max}")
    Integer maxRating;

    @Test
    public void throwsNotFoundExceptionWhenNonexistentIdGivenToGet() throws Exception {
        String notExistingId = "9821hjns8a98sj";

        mvc.perform(get("/rate/movie/{movieId}", notExistingId).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void throwsNotFoundExceptionWhenNonexistentIdGivenToGetFiltered() throws Exception {
        String notExistingId = "9821hjns8a98sj";
        List<String> notExistingIds = new ArrayList<>(Arrays.asList(notExistingId));
        String notExistingIdsJson = gson.toJson(notExistingIds);

        mvc.perform(post("/rate/movie/filterIds")
                .contentType(MediaType.APPLICATION_JSON)
                .content(notExistingIdsJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void throwsNotFoundExceptionWhenNonexistentIdGivenToPutRating() throws Exception {
        String notExistingId = "9821hjns8a98sj";
        Integer sampleRating = 5;
        String sampleRatingJson = gson.toJson(sampleRating);

        mvc.perform(put("/rate/movie/{movieId}", notExistingId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(sampleRatingJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void throwsNotValidRateExceptionWhenNotValidRateGiven() throws Exception {
       Rate notValidRate = new Rate();
       String notValidRateJson = gson.toJson(notValidRate);

        mvc.perform(post("/rate/movie")
                .contentType(MediaType.APPLICATION_JSON)
                .content(notValidRateJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void throwsRatingValueOutOfAllowedRangeWhenValueGivenIsOutOfRange() throws Exception {
        Integer ratingOutOfRange = maxRating + 1;
        String sampleId = "123";
        String ratingOutOfRangeJson = gson.toJson(ratingOutOfRange);

        mvc.perform(put("/rate/movie/{movieId}", sampleId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ratingOutOfRangeJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotAcceptable());
    }

}
