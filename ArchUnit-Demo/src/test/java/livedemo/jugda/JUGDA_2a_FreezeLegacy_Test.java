package livedemo.jugda;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.freeze.FreezingArchRule;
import org.junit.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class JUGDA_2a_FreezeLegacy_Test {

    @Test
    public void impl_resides_in_backend() {

        JavaClasses classes = new ClassFileImporter().importPackages("com.muchsoft");

        ArchRule rule = classes()
                .that().haveNameMatching(".*Impl")
                .should().resideInAPackage("..backend..")
                .orShould().beAnnotatedWith(Deprecated.class);

        ArchRule frozenRule = FreezingArchRule.freeze(rule);

        frozenRule.check(classes);
    }
}
