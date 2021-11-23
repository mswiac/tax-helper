package tax.helper.domain.core.dividend.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import tax.helper.domain.core.common.model.CurrencyAndAmount;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class DividendWithTax {

    private Dividend dividend;
    private CurrencyAndAmount dividendAmount;
    private CurrencyAndAmount dividendAmountInPln;
    private CurrencyAndAmount withholdingTax;
    private CurrencyAndAmount withholdingTaxInPln;
    private CurrencyAndAmount wholeTaxToPay;
    private CurrencyAndAmount taxToPayInPoland;

    @SuppressWarnings("unused")
    @Builder
    private static DividendWithTax of(
        Dividend dividend, CurrencyAndAmount dividendAmount, CurrencyAndAmount dividendAmountInPln,
        CurrencyAndAmount withholdingTax, CurrencyAndAmount withholdingTaxInPln, CurrencyAndAmount wholeTaxToPay,
        CurrencyAndAmount taxToPayInPoland
    ) {
        return new DividendWithTax(dividend, dividendAmount, dividendAmountInPln, withholdingTax, withholdingTaxInPln,
            wholeTaxToPay, taxToPayInPoland
        );
    }
}
