package tax.helper.domain.core.dividend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import tax.helper.domain.core.common.model.CurrencyAndAmount;

import java.time.LocalDate;

@Getter
@EqualsAndHashCode
@AllArgsConstructor(staticName = "of")
public class Dividend {

    private String ticker;
    private LocalDate paymentDay;
    private CurrencyAndAmount currencyAndAmount;
    private int shares;

    @SuppressWarnings("unused")
    private Dividend() {
    }
}

