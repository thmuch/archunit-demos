package archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.onionArchitecture;

//@RunWith(ArchUnitRunner.class) // necessary for JUnit 4
@AnalyzeClasses(packages = "oniondemo")
public class ArchUnit_6a_Onion_Test {

    @ArchTest
    public void adhere_to_onion_architecture(JavaClasses importedClasses) {

        ArchRule rule = onionArchitecture()
                .domainModels("oniondemo.domain.model..")
                .domainServices("oniondemo.domain.service..")
                .applicationServices("oniondemo.application..")
                .adapter("DB", "oniondemo.adapter.persistence..")
                .adapter("Web", "oniondemo.adapter.web");
//                .adapter("REST", "oniondemo.adapter.rest..")
//                .adapter("SOAP", "oniondemo.adapter.soap..")
//                .adapter("UI", "oniondemo.adapter.jsf..").;

        rule.check(importedClasses);
    }

    @ArchTest
    public void no_framework_dependencies_in_domain(JavaClasses importedClasses) {

        ArchRule rule = noClasses()
                .that().resideInAPackage("oniondemo.domain..")
                .should().dependOnClassesThat().resideInAPackage("org.springframework..")
                .because("our domain core should be independent of frameworks");

        rule.check(importedClasses);
    }
}
