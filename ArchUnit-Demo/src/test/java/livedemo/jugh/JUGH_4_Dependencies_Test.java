package livedemo.jugh;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class JUGH_4_Dependencies_Test {

    @Test
    public void backend_does_not_access_frontend() {

        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.muchsoft");

        ArchRule rule = noClasses()
                .that().resideInAPackage("..backend..")
                .should().dependOnClassesThat().resideInAnyPackage("..frontend..");

        rule.check(importedClasses);
    }
}
