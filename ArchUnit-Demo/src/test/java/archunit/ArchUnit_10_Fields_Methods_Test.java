package archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.thirdparty.com.google.common.annotations.VisibleForTesting;
import org.junit.Test;

import javax.persistence.Entity;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noFields;
import static extensions.Conditions.beAccessedFromTestClassesOnly;
import static extensions.Conditions.beAnnotatedWithEagerFetching;
import static extensions.Predicates.areAnnotatedWith;
import static extensions.Predicates.belongToAClassAnnotatedWith;

public class ArchUnit_10_Fields_Methods_Test {

    @Test
    public void check_fields() {

        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.muchsoft");

        ArchRule rule = noFields()
                .that(belongToAClassAnnotatedWith(Entity.class))
                .should(beAnnotatedWithEagerFetching());

        rule.check(importedClasses);
    }

    @Test
    public void check_methods() {

        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.muchsoft");

        ArchRule rule = methods()
                .that(areAnnotatedWith(VisibleForTesting.class))
                .should(beAccessedFromTestClassesOnly());

        rule.check(importedClasses);
    }
}
