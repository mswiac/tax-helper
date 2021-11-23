package tax.helper.domain.core.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TaxHelperConstants {

    // --------------------------------- Spring boot --------------------------------- //

    public static final String SPRING_BOOT_BASE_PACKAGE = "tax.helper.application.infrastructure";
    public static final String ENTITY_BASE_PACKAGE = "tax.helper.application.infrastructure";

    // --------------------------------- Application --------------------------------- //

    public static final String CLIENT_ADDRESS = "http://localhost:4200";

    // -------------------------------- NBP Endpoints -------------------------------- //

    public static final String NBP_AVERAGE_EXCHANGE_RATE_API_PATH_FORMAT = "http://api.nbp.pl/api/exchangerates/rates/a/%s/%s/?format=json";

    public static final String NBP_PATH = "/taxHelper/nbp";
    public static final String AVERAGE_EXCHANGE_RATE_PATH = "avrExchRate/{currency}/{currencyDate}";

    public static final String CURRENCY_PARAM_NAME = "currency";
    public static final String CURRENCY_DATE_PARAM_NAME = "currencyDate";

    // ------------------------------ Dividend Endpoints ----------------------------- //

    public static final String DIVIDEND_PATH = "/taxHelper/dividend";
    public static final String ADD_DIVIDEND_PATH = "/taxHelper/addDividend/{dividend}";
}
