package tax.helper.domain.core.nbp.model;

import java.util.List;

import lombok.Data;

@Data
public class ExchangeRatesSeries {

    private final String table;
    private final String currency;
    private final String code;
    private final List<Rate> rates;
}
