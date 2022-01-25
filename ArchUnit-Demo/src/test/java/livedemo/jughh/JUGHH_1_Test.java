package livedemo.jughh;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import jakarta.persistence.Entity;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class JUGHH_1_Test {

    @Test
    void myFirstArchUnitTest() {

        JavaClasses importedClasses = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("com.muchsoft.demo");

//        JavaClass javaClass = importedClasses.get(OrderBean.class);
//        Set<JavaAccess<?>> accessesToSelf = javaClass.getAccessesToSelf();

        ArchRule rule = classes()
                .that().resideInAPackage("..api..")
                .should().bePublic();

        rule.check(importedClasses);
    }

    @Test
    void no_entities_in_api() {

        JavaClasses importedClasses = new ClassFileImporter()
                .importPackages("com.muchsoft.demo");

        ArchRule rule = classes()
                .that().resideInAPackage("..api..")
                .should().notBeAnnotatedWith(Entity.class);

        rule.check(importedClasses);
    }
}
