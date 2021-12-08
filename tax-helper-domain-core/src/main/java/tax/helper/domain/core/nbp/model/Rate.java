package tax.helper.domain.core.nbp.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
@EqualsAndHashCode
public class Rate {

    private final String no;
    private final LocalDate effectiveDate;
    private final double mid;
}
