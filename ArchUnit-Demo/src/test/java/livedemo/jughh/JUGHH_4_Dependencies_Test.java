package livedemo.jughh;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.muchsoft.demo")
public class JUGHH_4_Dependencies_Test {

    @ArchTest
    void backend_does_not_access_frontend(JavaClasses importedClasses) {

        ArchRule rule = noClasses()
                .that().resideInAPackage("..backend..")
                .should().dependOnClassesThat().resideInAPackage("..frontend..");

        rule.check(importedClasses);
    }
}
