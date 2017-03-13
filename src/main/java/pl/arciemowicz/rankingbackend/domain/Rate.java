package pl.arciemowicz.rankingbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by bartosz_arciemowicz on 13/03/2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rate {

    String id;
    Type type;
    Double value;
}
