package pl.arciemowicz.rankingbackend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by bartosz_arciemowicz on 14/03/2017.
 */
@Configuration
public class ApplicationConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("ranking-backend")
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/rate.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Ranking Backend")
                .contact("Bartosz Arciemowicz")
                .version("1.0")
                .build();
    }
}
