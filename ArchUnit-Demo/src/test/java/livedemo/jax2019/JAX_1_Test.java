package livedemo.jax2019;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;

import javax.ejb.Stateful;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class JAX_1_Test {

    @Test
    public void myFirstArchUnitTest() {

        JavaClasses javaClasses = new ClassFileImporter().importPackages("com.muchsoft");

        ArchRule rule = classes().that().resideInAPackage("..api..")
                .should().bePublic();

        rule.check(javaClasses);
    }

    @Test
    public void noStatefulEJBs() {
        JavaClasses javaClasses = new ClassFileImporter().importPackages("com.muchsoft");

        ArchRule rule = classes().should().notBeAnnotatedWith(Stateful.class);

        rule.check(javaClasses);
    }
}
