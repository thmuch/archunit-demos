package livedemo.gedoplan;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class GEDOPLAN_2_Test {

    @Test
    void impl_in_backend() {

        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.muchsoft.demo");

        ArchRule rule = classes()
                .that().haveSimpleNameEndingWith("Impl")
                .should().resideInAPackage("..backend..")
                .orShould().beAnnotatedWith(Deprecated.class);

        rule.check(importedClasses);
    }
}
