package tax.helper.application.standalone;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import tax.helper.domain.core.common.TaxHelperConstants;

@SpringBootApplication(scanBasePackages = TaxHelperConstants.SPRING_BOOT_BASE_PACKAGE)
@EntityScan(basePackages = TaxHelperConstants.ENTITY_BASE_PACKAGE)
public class TaxHelper implements CommandLineRunner {

    public static void main(final String[] args) {
        SpringApplication.run(TaxHelper.class, args);
    }

    @Override
    public void run(final String... args) {
    }
}
