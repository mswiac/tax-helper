package tax.helper.application.infrastructure.endpoint.dividend;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;
import tax.helper.domain.application.dividend.AddDividendCommand;
import tax.helper.domain.core.common.TaxHelperConstants;
import tax.helper.domain.core.dividend.model.Dividend;
import tax.helper.domain.core.dividend.model.DividendWithTax;
import tax.helper.domain.core.nbp.NbpClient;

@CrossOrigin(origins = TaxHelperConstants.CLIENT_ADDRESS)
@RequestMapping(TaxHelperConstants.DIVIDEND_PATH)
@AllArgsConstructor(staticName = "of")
public class DividendHttpEndpoint {

    private final NbpClient nbpClient;

    @PostMapping(TaxHelperConstants.ADD_DIVIDEND_PATH)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public DividendWithTax addDividend(@RequestBody Dividend dividend) {
        AddDividendCommand addDividendCommand = AddDividendCommand.of(nbpClient, dividend);

        return addDividendCommand.handle();
    }
}
