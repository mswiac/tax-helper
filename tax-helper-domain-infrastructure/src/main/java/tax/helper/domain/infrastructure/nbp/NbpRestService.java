package tax.helper.domain.infrastructure.nbp;

import java.time.LocalDate;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import tax.helper.domain.core.common.TaxHelperConstants;
import tax.helper.domain.core.common.json.JsonConverter;
import tax.helper.domain.core.common.model.Currency;
import tax.helper.domain.core.nbp.NbpClient;
import tax.helper.domain.core.nbp.model.ExchangeRatesSeries;

public final class NbpRestService implements NbpClient {

    private final RestTemplate restTemplate;

    private NbpRestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public static NbpRestService of(RestTemplateBuilder restTemplateBuilder) {
        return new NbpRestService(restTemplateBuilder.build());
    }

    @Override
    public ExchangeRatesSeries getAverageExchangeRate(Currency currency, LocalDate currencyDate) {
        String url = String.format(
            TaxHelperConstants.NBP_AVERAGE_EXCHANGE_RATE_API_PATH_FORMAT, currency, currencyDate);
        String response = this.restTemplate.getForObject(url, String.class);

        return JsonConverter.jsonToObject(response, ExchangeRatesSeries.class);
    }
}
