package co.gov.and.mintic.sigmi;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("co.gov.and.mintic.sigmi");

        noClasses()
            .that()
            .resideInAnyPackage("co.gov.and.mintic.sigmi.service..")
            .or()
            .resideInAnyPackage("co.gov.and.mintic.sigmi.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..co.gov.and.mintic.sigmi.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
