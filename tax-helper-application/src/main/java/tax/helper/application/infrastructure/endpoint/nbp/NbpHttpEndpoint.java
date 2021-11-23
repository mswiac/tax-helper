package tax.helper.application.infrastructure.endpoint.nbp;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import tax.helper.domain.application.nbp.query.GetAverageExchangeRateForDayQuery;
import tax.helper.domain.core.common.TaxHelperConstants;
import tax.helper.domain.core.nbp.NbpClient;
import tax.helper.domain.core.nbp.model.ExchangeRatesSeries;
import tax.helper.domain.core.common.model.Currency;

@CrossOrigin(origins = TaxHelperConstants.CLIENT_ADDRESS)
@RequestMapping(TaxHelperConstants.NBP_PATH)
@AllArgsConstructor(staticName = "of")
public class NbpHttpEndpoint {

    private final NbpClient nbpClient;

    @GetMapping(TaxHelperConstants.AVERAGE_EXCHANGE_RATE_PATH)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ExchangeRatesSeries getAverageExchangeRate(
        @PathVariable(TaxHelperConstants.CURRENCY_PARAM_NAME) Currency currency,
        @PathVariable(TaxHelperConstants.CURRENCY_DATE_PARAM_NAME) @ApiParam(example = "2021-10-22") String currencyDate
    ) {
        GetAverageExchangeRateForDayQuery getAverageExchangeRateForDayQuery = GetAverageExchangeRateForDayQuery.of(
            nbpClient, currency, currencyDate);

        return getAverageExchangeRateForDayQuery.handle();
    }
}
