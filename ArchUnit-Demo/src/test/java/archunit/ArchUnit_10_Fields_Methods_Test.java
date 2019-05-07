package archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.thirdparty.com.google.common.annotations.VisibleForTesting;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.Entity;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noFields;
import static extensions.Conditions.beAccessedFromTestClassesOnly;
import static extensions.Conditions.beAnnotatedWithEagerFetching;
import static extensions.Predicates.areAnnotatedWith;
import static extensions.Predicates.belongToAClassAnnotatedWith;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.muchsoft")
public class ArchUnit_10_Fields_Methods_Test {

    @ArchTest
    public void check_fields(JavaClasses importedClasses) {

        ArchRule rule = noFields()
                .that(belongToAClassAnnotatedWith(Entity.class))
                .should(beAnnotatedWithEagerFetching());

        rule.check(importedClasses);
    }

    @ArchTest
    public void check_methods(JavaClasses importedClasses) {

        ArchRule rule = methods()
                .that(areAnnotatedWith(VisibleForTesting.class))
                .should(beAccessedFromTestClassesOnly());

        rule.check(importedClasses);
    }
}
