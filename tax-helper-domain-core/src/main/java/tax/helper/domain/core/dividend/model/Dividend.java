package tax.helper.domain.core.dividend.model;

import java.time.LocalDate;

import lombok.Data;
import tax.helper.domain.core.common.model.CurrencyAndAmount;

@Data
public class Dividend {

    private String ticker;
    private LocalDate paymentDay;
    private CurrencyAndAmount currencyAndAmount;
    private int shares;

    @SuppressWarnings("unused")
    private Dividend() {
    }
}
