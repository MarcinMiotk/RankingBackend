package pl.arciemowicz.rankingbackend.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by bartosz_arciemowicz on 14/03/2017.
 */
public interface RateRepository extends MongoRepository<Rate, String> {

    Rate findByTypeAndId(Type type, String firstName);

    List<Rate> findAllByTypeOrderByAverageDesc(Type type);
}
