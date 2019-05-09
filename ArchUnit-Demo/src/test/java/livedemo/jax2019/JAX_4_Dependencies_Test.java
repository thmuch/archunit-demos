package livedemo.jax2019;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class JAX_4_Dependencies_Test {

    @Test
    public void backendShouldBeIndependentOfFrontend() {

        JavaClasses javaClasses = new ClassFileImporter().importPackages("com.muchsoft");

        ArchRule rule = noClasses().that().resideInAPackage("..backend..")
                .should().dependOnClassesThat().resideInAPackage("..frontend..");

        rule.check(javaClasses);
    }
}
