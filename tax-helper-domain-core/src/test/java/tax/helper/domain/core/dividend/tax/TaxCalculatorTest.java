package tax.helper.domain.core.dividend.tax;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tax.helper.domain.core.common.model.Currency;
import tax.helper.domain.core.common.model.CurrencyAndAmount;
import tax.helper.domain.core.dividend.model.Dividend;
import tax.helper.domain.core.dividend.model.DividendWithTax;
import tax.helper.domain.core.nbp.NbpClient;
import tax.helper.domain.core.nbp.model.ExchangeRatesSeries;
import tax.helper.domain.core.nbp.model.Rate;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TaxCalculatorTest {

    private static final double TAX_RATE_USA = 0.15;
    private static final double TAX_RATE_POLAND = 0.19;

    NbpClient nbpClient = Mockito.mock(NbpClient.class);

    @Test
    void shouldCalculateTaxForSingleShare() {
        // GIVEN
        Mockito.when(nbpClient.getAverageExchangeRate(Currency.USD, LocalDate.of(2021, 11, 8)))
            .thenReturn(getExchangeRateForMedifast());

        Dividend medifastDividend = getMedifastDividend();
        TaxCalculator taxCalculator = TaxCalculator.of(nbpClient, medifastDividend, TAX_RATE_USA, TAX_RATE_POLAND);

        // WHEN
        DividendWithTax expectedDividendWithTax = getMedifastDividendWithTax();
        DividendWithTax medifastDividendWithTax = taxCalculator.calculateTax();

        // THEN
        assertThat(medifastDividendWithTax).isEqualTo(expectedDividendWithTax);
    }

    private Dividend getMedifastDividend() {
        return Dividend.of(
            "MED",
            LocalDate.of(2021, 11, 8),
            CurrencyAndAmount.of(Currency.USD, 1.42),
            1
        );
    }

    private DividendWithTax getMedifastDividendWithTax() {
        return DividendWithTax.builder()
            .dividend(getMedifastDividend())
            .dividendAmount(CurrencyAndAmount.of(Currency.USD, 1.42))
            .dividendAmountInPln(CurrencyAndAmount.of(Currency.PLN, 5.67))
            .withholdingTax(CurrencyAndAmount.of(Currency.USD, 0.21))
            .withholdingTaxInPln(CurrencyAndAmount.of(Currency.PLN, 0.85))
            .wholeTaxToPay(CurrencyAndAmount.of(Currency.PLN, 1.08))
            .taxToPayInPoland(CurrencyAndAmount.of(Currency.PLN, 0.23))
            .build();
    }

    private ExchangeRatesSeries getExchangeRateForMedifast() {
        return ExchangeRatesSeries.builder()
            .code(Currency.USD.name())
            .rates(List.of(
                Rate.builder()
                    .effectiveDate(LocalDate.of(2021, 11, 8))
                    .mid(3.9911)
                    .build()
            ))
            .build();
    }

    private Dividend getAbbvieDividend() {
        return Dividend.of(
            "ABBV",
            LocalDate.of(2021, 11, 15),
            CurrencyAndAmount.of(Currency.USD, 1.30),
            2
        );
    }

    private DividendWithTax getAbbvieDividendWithTax() {
        return DividendWithTax.builder()
            .dividend(getAbbvieDividend())
            .dividendAmount(CurrencyAndAmount.of(Currency.USD, 1.30))
            .dividendAmountInPln(CurrencyAndAmount.of(Currency.PLN, 10.55))
            .withholdingTax(CurrencyAndAmount.of(Currency.USD, 0.39))
            .withholdingTaxInPln(CurrencyAndAmount.of(Currency.PLN, 1.58))
            .wholeTaxToPay(CurrencyAndAmount.of(Currency.PLN, 2.00))
            .taxToPayInPoland(CurrencyAndAmount.of(Currency.PLN, 0.42))
            .build();
    }

    private ExchangeRatesSeries getExchangeRateForAbbvie() {
        return ExchangeRatesSeries.builder()
            .code(Currency.USD.name())
            .rates(List.of(
                Rate.builder()
                    .effectiveDate(LocalDate.of(2021, 11, 15))
                    .mid(4.0559)
                    .build()
            ))
            .build();
    }
}