package tax.helper.application.infrastructure.configuration.http;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tax.helper.application.infrastructure.endpoint.dividend.DividendHttpEndpoint;
import tax.helper.application.infrastructure.endpoint.nbp.NbpHttpEndpoint;
import tax.helper.domain.core.nbp.NbpClient;
import tax.helper.domain.infrastructure.nbp.NbpRestService;

@Configuration
public class TaxHelperHttpClientConfiguration {

    @Bean
    public NbpClient nbpRestService(RestTemplateBuilder restTemplateBuilder) {
        return NbpRestService.of(restTemplateBuilder);
    }

    @Bean
    public NbpHttpEndpoint nbpHttpEndpoint(NbpClient nbpRestService) {
        return NbpHttpEndpoint.of(nbpRestService);
    }

    @Bean
    public DividendHttpEndpoint dividendHttpEndpoint(NbpClient nbpClient) {
        return DividendHttpEndpoint.of(nbpClient);
    }
}
