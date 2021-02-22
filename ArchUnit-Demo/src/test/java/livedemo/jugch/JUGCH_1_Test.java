package livedemo.jugch;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.Priority;
import jakarta.ejb.Stateful;
import org.junit.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.priority;

public class JUGCH_1_Test {

    @Test
    public void myFirstArchUnitTest() {

        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.muchsoft.demo");

        ArchRule rule = classes()
                .that().resideInAPackage("..api..")
                .should().bePublic();

        rule.check(importedClasses);
    }

    @Test
    public void no_stateful_EJBs() {

        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.muchsoft.demo");

        ArchRule rule = priority(Priority.HIGH).classes()
                .should().notBeAnnotatedWith(Stateful.class)
                .because("they don't scale well");

        rule.check(importedClasses);
    }
}
