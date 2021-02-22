package livedemo.jcon2020;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import jakarta.ejb.Stateful;
import org.junit.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class JCON_1_Test {

    @Test
    public void myFirstArchUnitTest() {

        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.muchsoft");

        ArchRule rule = classes()
                .that().resideInAPackage("..api..")
                .should().bePublic();

        rule.check(importedClasses);
    }

    @Test
    public void no_stateful_EJBs() {

        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.muchsoft");

        ArchRule rule = classes()
                .should().notBeAnnotatedWith(Stateful.class)
                .because("they don't scale well");

        rule.check(importedClasses);
    }
}
