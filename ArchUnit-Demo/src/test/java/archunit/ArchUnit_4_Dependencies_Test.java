package archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.muchsoft")
public class ArchUnit_4_Dependencies_Test {

    @ArchTest
    public void backend_does_not_access_frontend(JavaClasses importedClasses) {

        ArchRule rule = noClasses()
                .that().resideInAPackage("..backend..")
                .should().dependOnClassesThat().resideInAPackage("..frontend..");

        rule.check(importedClasses);
    }

    @ArchTest
    public void api_is_only_accessed_by_frontend_and_backend(JavaClasses importedClasses) {

        ArchRule rule = classes()
                .that().resideInAPackage("..api..")
                .should().onlyHaveDependentClassesThat().resideInAnyPackage("..frontend..","..backend..");

        rule.check(importedClasses);
    }

    @ArchTest
    public void repositories_are_only_used_by_services(JavaClasses importedClasses) {

//        ArchRule rule = classes()
//                .that().haveSimpleNameContaining("Repository")
//                .should().onlyBeAccessed().byClassesThat().haveSimpleNameContaining("Service");

        ArchRule rule = classes()
                .that().haveSimpleNameContaining("Repository")
                .should().onlyHaveDependentClassesThat().haveSimpleNameContaining("Service");

        rule.check(importedClasses);
    }
}
