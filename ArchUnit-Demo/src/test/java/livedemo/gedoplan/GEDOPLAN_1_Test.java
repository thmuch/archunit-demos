package livedemo.gedoplan;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import jakarta.ejb.Stateful;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class GEDOPLAN_1_Test {

    @Test
    void myFirstArchUnitTest() {

        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.muchsoft.demo");

//        importedClasses.get(OrderBean.class).getAccessesFromSelf();

        ArchRule rule = classes()
                .that().resideInAPackage("..api..")
                .should().bePublic();

        rule.check(importedClasses);
    }

    @Test
    void no_stateful_EJBs() {

        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.muchsoft.demo");

        ArchRule rule = classes()
                .should().notBeAnnotatedWith(Stateful.class)
                .because("they don't scale well (see adr/no-stateful-ejb.md)");

        rule.check(importedClasses);
    }
}
