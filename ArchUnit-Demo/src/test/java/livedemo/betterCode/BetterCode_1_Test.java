package livedemo.betterCode;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class BetterCode_1_Test {

    @Test
    void meinErsterArchUnitTest() {

        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.muchsoft");

        ArchRule rule = classes()
                .that().resideInAPackage("..api..")
                .should().bePublic();

        rule.check(importedClasses);
    }

    @Test
    void impl_in_backend_only() {

        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.muchsoft");

        ArchRule rule = classes()
                .that().haveSimpleNameEndingWith("Impl")
                .should().resideInAPackage("..backend..")
                .orShould().beAnnotatedWith(Deprecated.class)
                .because("we don't want implementation details in api or frontend (see docs/adr/0001-impl-in-backend.md)");

        rule.check(importedClasses);
    }
}
