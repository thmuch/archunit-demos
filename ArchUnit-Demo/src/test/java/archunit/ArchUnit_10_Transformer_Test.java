package archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.thirdparty.com.google.common.annotations.VisibleForTesting;
import org.junit.Test;

import javax.persistence.Entity;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.all;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.no;
import static extensions.ClassTransformers.fields;
import static extensions.ClassTransformers.methods;
import static extensions.Conditions.beAccessedFromTestClassesOnly;
import static extensions.Conditions.beAnnotatedWithEagerFetching;
import static extensions.Predicates.areAnnotatedWith;
import static extensions.Predicates.belongToAClassAnnotatedWith;

public class ArchUnit_10_Transformer_Test {

    @Test
    public void check_fields() {

        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.muchsoft");

        ArchRule rule = no(fields())
                .that(belongToAClassAnnotatedWith(Entity.class))
                .should(beAnnotatedWithEagerFetching());

        rule.check(importedClasses);
    }

    @Test
    public void check_methods() {

        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.muchsoft");

        ArchRule rule = all(methods())
                .that(areAnnotatedWith(VisibleForTesting.class))
                .should(beAccessedFromTestClassesOnly());

        rule.check(importedClasses);
    }
}
