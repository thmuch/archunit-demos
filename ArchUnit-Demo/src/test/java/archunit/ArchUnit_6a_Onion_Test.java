package archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchIgnore;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

import static com.tngtech.archunit.library.Architectures.onionArchitecture;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.muchsoft")
public class ArchUnit_6a_Onion_Test {

    @ArchTest
    @ArchIgnore
    public void adhere_to_onion_architecture(JavaClasses importedClasses) {

        ArchRule rule = onionArchitecture()
                .domainModels("com.muchsoft.myapp.domain.model..")
                .domainServices("com.muchsoft.myapp.domain.service..")
                .applicationServices("com.muchsoft.myapp.application..")
                .adapter("DB", "com.muchsoft.myapp.adapter.persistence..")
                .adapter("REST", "com.muchsoft.myapp.adapter.rest..")
                .adapter("SOAP", "com.muchsoft.myapp.adapter.soap..")
                .adapter("UI", "com.muchsoft.myapp.adapter.jsf..");

        rule.check(importedClasses);
    }
}
