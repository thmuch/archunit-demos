package livedemo.jugda;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class JUGDA_2_Test {

    @Test
    public void impl_resides_in_backend() {

        JavaClasses classes = new ClassFileImporter().importPackages("com.muchsoft");

        ArchRule rule = classes()
                .that().haveNameMatching(".*Impl")
                .should().resideInAPackage("..backend..")
                .orShould().beAnnotatedWith(Deprecated.class);

        rule.check(classes);
    }
}
