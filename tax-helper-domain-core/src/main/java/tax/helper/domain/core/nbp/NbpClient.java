package tax.helper.domain.core.nbp;

import tax.helper.domain.core.nbp.model.ExchangeRatesSeries;
import tax.helper.domain.core.common.model.Currency;

import java.time.LocalDate;

public interface NbpClient {

    ExchangeRatesSeries getAverageExchangeRate(Currency currency, LocalDate currencyDate);
}
