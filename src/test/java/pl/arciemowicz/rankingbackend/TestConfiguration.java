package pl.arciemowicz.rankingbackend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import pl.arciemowicz.rankingbackend.service.RatesService;

import static org.mockito.Mockito.*;

/**
 * Created by bartosz_arciemowicz on 13/03/2017.
 */
@Profile("test")
@Configuration
public class TestConfiguration {

    @Bean
    @Primary
    public RatesService ratesService() {
        return mock(RatesService.class);
    }
}