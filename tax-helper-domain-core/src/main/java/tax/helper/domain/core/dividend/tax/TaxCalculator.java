package tax.helper.domain.core.dividend.tax;

import java.util.Optional;

import org.apache.commons.math3.util.Precision;

import tax.helper.domain.core.common.model.Currency;
import tax.helper.domain.core.common.model.CurrencyAndAmount;
import tax.helper.domain.core.dividend.model.Dividend;
import tax.helper.domain.core.dividend.model.DividendWithTax;
import tax.helper.domain.core.nbp.NbpClient;
import tax.helper.domain.core.nbp.model.ExchangeRatesSeries;

public class TaxCalculator {

    private final Dividend dividend;
    private final double taxRateInOriginCountry;
    private final double taxRateInPoland;

    private ExchangeRatesSeries exchangeRatesSeries;

    private TaxCalculator(
        NbpClient nbpClient, Dividend dividend, double taxRateInOriginCountry, double taxRateInPoland
    ) {
        this.dividend = dividend;
        this.taxRateInOriginCountry = taxRateInOriginCountry;
        this.taxRateInPoland = taxRateInPoland;
        getExchangeRatesSeries(nbpClient).ifPresent(
            averageCurrencyRate -> this.exchangeRatesSeries = averageCurrencyRate);
    }

    public static TaxCalculator of(
        NbpClient nbpClient, Dividend dividend, double taxRateInOriginCountry, double taxRateInPoland
    ) {
        return new TaxCalculator(nbpClient, dividend, taxRateInOriginCountry, taxRateInPoland);
    }


    public DividendWithTax calculateTax() {
        return DividendWithTax.builder()
            .dividend(dividend)
            .dividendAmount(CurrencyAndAmount.of(getDividendCurrency(), calculateDividendAmountInOriginCurrency()))
            .dividendAmountInPln(CurrencyAndAmount.of(Currency.PLN, calculateDividendAmountInPln()))
            .withholdingTax(CurrencyAndAmount.of(getDividendCurrency(), calculateWithholdingTax()))
            .withholdingTaxInPln(CurrencyAndAmount.of(Currency.PLN, calculateWithholdingTaxInPln()))
            .wholeTaxToPay(CurrencyAndAmount.of(Currency.PLN, calculateWholeTaxToPay()))
            .taxToPayInPoland(CurrencyAndAmount.of(Currency.PLN, calculateTaxToPayInPoland()))
            .build();
    }

    private Optional<ExchangeRatesSeries> getExchangeRatesSeries(NbpClient nbpClient) {
        if (getDividendCurrency() != Currency.PLN) {
            return Optional.of(nbpClient.getAverageExchangeRate(getDividendCurrency(), dividend.getPaymentDay()));
        }

        return Optional.empty();
    }

    private double calculateDividendAmountInOriginCurrency() {
        double amount = dividend.getShares() * getDividendAmount();

        return Precision.round(amount, 2);
    }

    private double calculateDividendAmountInPln() {
        double amount = dividend.getShares() * getDividendAmount() * getAverageExchangeRate();

        return Precision.round(amount, 2);
    }

    private double calculateWithholdingTax() {
        double amount = calculateDividendAmountInOriginCurrency() * taxRateInOriginCountry;

        return Precision.round(amount, 2);
    }

    private double calculateWithholdingTaxInPln() {
        double amount = calculateWithholdingTax() * getAverageExchangeRate();

        return Precision.round(amount, 2);
    }

    private double calculateWholeTaxToPay() {
        double amount = calculateDividendAmountInPln() * taxRateInPoland;

        return Precision.round(amount, 2);
    }

    private double calculateTaxToPayInPoland() {
        double amount = calculateWholeTaxToPay() - calculateWithholdingTaxInPln();
        amount = Math.max(amount, 0.0);

        return Precision.round(amount, 2);
    }

    private Currency getDividendCurrency() {
        return dividend.getCurrencyAndAmount()
            .getCurrency();
    }

    private double getDividendAmount() {
        return dividend.getCurrencyAndAmount()
            .getAmount();
    }

    private double getAverageExchangeRate() {
        return getDividendCurrency() == Currency.PLN ? 1.0 : exchangeRatesSeries.getRates()
            .get(0)
            .getMid();
    }
}
