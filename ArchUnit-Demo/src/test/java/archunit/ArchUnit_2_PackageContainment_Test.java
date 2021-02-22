package archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import jakarta.persistence.Entity;
import org.junit.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class ArchUnit_2_PackageContainment_Test {

    @Test
    public void implResidesInTheBackend_orIsLegacy() {

        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.muchsoft");

        ArchRule rule = classes().that().haveNameMatching(".*Impl")
                .should().resideInAPackage("..backend..")
                .orShould().beAnnotatedWith(Deprecated.class);

        rule.check(importedClasses);
    }

    @Test
    public void entities_reside_in_model_packages() {

        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.muchsoft");

        ArchRule rule = classes().that().areAnnotatedWith(Entity.class)
                .should().resideInAPackage("..model..");

        rule.check(importedClasses);
    }
}
