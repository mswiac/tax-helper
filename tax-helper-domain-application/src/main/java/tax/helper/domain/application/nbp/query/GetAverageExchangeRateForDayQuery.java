package tax.helper.domain.application.nbp.query;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import tax.helper.domain.core.common.model.Currency;
import tax.helper.domain.core.common.query.Query;
import tax.helper.domain.core.nbp.NbpClient;
import tax.helper.domain.core.nbp.model.ExchangeRatesSeries;

@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetAverageExchangeRateForDayQuery implements Query<ExchangeRatesSeries> {

    private NbpClient nbpClient;

    private Currency currency;

    private String currencyDate;

    @Override
    public ExchangeRatesSeries handle() {
        LocalDate localDate = LocalDate.parse(currencyDate);

        return nbpClient.getAverageExchangeRate(currency, localDate);
    }
}
