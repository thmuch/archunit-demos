package livedemo.jaxlondon2020;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import jakarta.ejb.Stateful;
import org.junit.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class JAXLondon_1_Test {

    @Test
    public void myFirstArchUnitTest() {

        JavaClasses classes = new ClassFileImporter().importPackages("com.muchsoft");

        ArchRule rule = classes()
                .that().resideInAPackage("..api..")
                .should().bePublic();

        rule.check(classes);
    }

    @Test
    public void no_stateful_ejbs() {

        JavaClasses classes = new ClassFileImporter().importPackages("com.muchsoft");

        ArchRule rule = classes()
                .should().notBeAnnotatedWith(Stateful.class)
                .because("they don't scale well");

        rule.check(classes);
    }
}
