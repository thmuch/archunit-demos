package archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.thirdparty.com.google.common.annotations.VisibleForTesting;

import javax.persistence.Entity;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noFields;
import static extensions.Conditions.beAccessedFromTestClassesOnly;
import static extensions.Conditions.beAnnotatedWithEagerFetching;

//@RunWith(ArchUnitRunner.class) // necessary for JUnit 4
@AnalyzeClasses(packages = "com.muchsoft")
public class ArchUnit_10_Fields_Methods_Test {

    @ArchTest
    public void check_fields(JavaClasses importedClasses) {

        ArchRule rule = noFields()
                .that().areDeclaredInClassesThat().areAnnotatedWith(Entity.class)
                .should(beAnnotatedWithEagerFetching());

        rule.check(importedClasses);
    }

    @ArchTest
    public void check_methods(JavaClasses importedClasses) {

        ArchRule rule = methods()
                .that().areAnnotatedWith(VisibleForTesting.class)
                .should(beAccessedFromTestClassesOnly());

        rule.check(importedClasses);
    }
}
