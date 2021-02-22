package livedemo.jugh;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.Priority;
import jakarta.persistence.Entity;
import org.junit.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.priority;

public class JUGH_2_Test {

    @Test
    public void impl_in_backend() {

        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.muchsoft");

        ArchRule rule = priority(Priority.LOW).classes()
                .that().haveNameMatching(".*Impl")
                .should().resideInAPackage("..backend..")
                .orShould().beAnnotatedWith(Deprecated.class);

        rule.check(importedClasses);
    }

    @Test
    public void entity_in_model() {

        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.muchsoft");

        ArchRule rule = classes().that().areAnnotatedWith(Entity.class).should().resideInAPackage("..model..");

        rule.check(importedClasses);
    }
}
