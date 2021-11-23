package tax.helper.domain.application.dividend;

import lombok.AllArgsConstructor;
import tax.helper.domain.core.common.command.Command;
import tax.helper.domain.core.common.model.Currency;
import tax.helper.domain.core.dividend.model.Dividend;
import tax.helper.domain.core.dividend.model.DividendWithTax;
import tax.helper.domain.core.dividend.tax.TaxCalculator;
import tax.helper.domain.core.nbp.NbpClient;

@AllArgsConstructor(staticName = "of")
public class AddDividendCommand implements Command<DividendWithTax> {

    private final NbpClient nbpClient;
    private final Dividend dividend;

    @Override
    public DividendWithTax handle() {
        TaxCalculator taxCalculator = TaxCalculator.of(nbpClient, dividend, getTaxRateForCurrency(
            dividend.getCurrencyAndAmount()
                .getCurrency()), getTaxRateForCurrency(Currency.PLN));

        return taxCalculator.calculateTax();
    }

    private double getTaxRateForCurrency(Currency currency) {
        double taxRate;
        switch (currency) {
            case PLN:
                taxRate = 0.19;
                break;
            case USD:
                taxRate = 0.15;
                break;
            default:
                taxRate = 0.0;
        }

        return taxRate;
    }
}
