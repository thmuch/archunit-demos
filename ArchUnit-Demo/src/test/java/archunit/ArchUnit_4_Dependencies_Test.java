package archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class ArchUnit_4_Dependencies_Test {

    @Test
    public void backend_does_not_access_frontend() {

        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.muchsoft");

        ArchRule rule = noClasses()
                .that().resideInAPackage("..backend..")
                .should().dependOnClassesThat().resideInAPackage("..frontend..");

        rule.check(importedClasses);
    }

    @Test
    public void api_is_only_accessed_by_frontend_and_backend() {

        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.muchsoft");

        ArchRule rule = classes()
                .that().resideInAPackage("..api..")
                .should().onlyHaveDependentClassesThat().resideInAnyPackage("..frontend..","..backend..");

        rule.check(importedClasses);
    }

    @Test
    public void repositories_are_only_used_by_services() {

        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.muchsoft");

//        ArchRule rule = classes()
//                .that().haveSimpleNameContaining("Repository")
//                .should().onlyBeAccessed().byClassesThat().haveSimpleNameContaining("Service");

        ArchRule rule = classes()
                .that().haveSimpleNameContaining("Repository")
                .should().onlyHaveDependentClassesThat().haveSimpleNameContaining("Service");

        rule.check(importedClasses);
    }
}
