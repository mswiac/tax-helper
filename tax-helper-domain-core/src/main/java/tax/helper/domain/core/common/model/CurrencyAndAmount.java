package tax.helper.domain.core.common.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class CurrencyAndAmount {

    private final Currency currency;
    private final double amount;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    private CurrencyAndAmount(@JsonProperty("currency") Currency currency, @JsonProperty("double") double amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public static CurrencyAndAmount of(Currency currency, double amount) {
        return new CurrencyAndAmount(currency, amount);
    }
}
