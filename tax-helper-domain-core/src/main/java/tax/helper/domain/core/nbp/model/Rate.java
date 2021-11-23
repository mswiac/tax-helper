package tax.helper.domain.core.nbp.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Rate {

    private final String no;
    private final LocalDate effectiveDate;
    private final double mid;
}
