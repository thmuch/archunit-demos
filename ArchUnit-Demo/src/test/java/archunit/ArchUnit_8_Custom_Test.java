package archunit;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import com.tngtech.archunit.lang.conditions.ArchConditions;
import org.junit.Test;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAPackage;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.simpleNameContaining;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class ArchUnit_8_Custom_Test {

    @Test
    public void custom_predicates_and_conditions() {

        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.muchsoft");

        // classes that PREDICATE should CONDITION

        ArchRule rule = classes().that(haveAFunnyName()).should(resideInAFunnyPackage());

        rule.check(importedClasses);
    }

    private DescribedPredicate<JavaClass> haveAFunnyName() {
        return new DescribedPredicate<JavaClass>("have a funny name") {
            @Override
            public boolean apply(JavaClass javaClass) {
                return javaClass.getSimpleName().toUpperCase().contains("FUN");
            }
        };
    }

    private ArchCondition<JavaClass> resideInAFunnyPackage() {
        return new ArchCondition<JavaClass>("reside in a funny package") {
            @Override
            public void check(JavaClass javaClass, ConditionEvents conditionEvents) {

                if (!javaClass.getPackageName().toUpperCase().contains("FUN")) {

                    String message =
                            String.format("Funny class %s resides in package %s, which is not funny",
                                    javaClass.getSimpleName(),
                                    javaClass.getPackageName());

                    conditionEvents.add(SimpleConditionEvent.violated(javaClass,message));
                }
            }
        };
    }
}
