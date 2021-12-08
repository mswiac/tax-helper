package tax.helper.domain.core.nbp.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
public class ExchangeRatesSeries {

    private final String table;
    private final String currency;
    private final String code;
    private final List<Rate> rates;
}
