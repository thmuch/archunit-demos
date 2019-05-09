package archunit;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import extensions.Immutable;
import org.junit.runner.RunWith;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAPackage;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.simpleNameContaining;
import static com.tngtech.archunit.lang.conditions.ArchConditions.beAnnotatedWith;
import static com.tngtech.archunit.lang.conditions.ArchConditions.haveOnlyFinalFields;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.muchsoft")
public class ArchUnit_9_Predefined_Test {

    @ArchTest
    public void predefined_predicates_and_conditions(JavaClasses importedClasses) {

        DescribedPredicate<JavaClass> haveAnythingDoToWithUser =
                simpleNameContaining("User").or(resideInAPackage("..user.."));

        ArchCondition<JavaClass> beImmutable =
                haveOnlyFinalFields().and(beAnnotatedWith(Immutable.class));

        ArchRule rule = classes()
                .that(haveAnythingDoToWithUser)
                .should(beImmutable);

        rule.check(importedClasses);
    }
}
