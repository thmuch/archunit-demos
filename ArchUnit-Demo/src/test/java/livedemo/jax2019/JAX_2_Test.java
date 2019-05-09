package livedemo.jax2019;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class JAX_2_Test {

    @Test
    public void implShouldBeInBackend() {

        JavaClasses javaClasses = new ClassFileImporter().importPackages("com.muchsoft");

        ArchRule rule = classes().that().haveSimpleNameEndingWith("Impl")
                .should().resideInAPackage("..backend..")
                .orShould().beAnnotatedWith(Deprecated.class);

        rule.check(javaClasses);
    }
}
