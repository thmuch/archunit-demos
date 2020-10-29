package livedemo.jugch;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class JUGCH_2_Test {

    @Test
    public void impl_in_backend_only() {

        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.muchsoft.demo");

        ArchRule rule = classes()
                .that().haveNameMatching(".*Impl")
                .should().resideInAPackage("..backend..")
                .orShould().beAnnotatedWith(Deprecated.class);

        rule.check(importedClasses);
    }
}
