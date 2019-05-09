package livedemo.jax2019;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchRules;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import org.junit.runner.RunWith;

import javax.ejb.Stateful;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.muchsoft")
public class JAX_6_Custom_Test {

    @ArchTest
    public void customConditionsAndPredicates(JavaClasses javaClasses) {

        // classes that PREDICATE should CONDITION

        ArchRule rule = classes().that(haveAFunnyName())
                .should(resideInAFunnyPackage());

        rule.check(javaClasses);
    }

    private ArchCondition<JavaClass> resideInAFunnyPackage() {
        return new ArchCondition<JavaClass>("reside in a funny package") {
            @Override
            public void check(JavaClass javaClass, ConditionEvents conditionEvents) {

                if (!javaClass.getPackageName().toUpperCase().contains("FUN")) {

                    String message = String.format("Class %s resides in a package %s" +
                            " which is not funny",javaClass.getSimpleName(),
                            javaClass.getPackageName());

                    conditionEvents.add(SimpleConditionEvent.violated(javaClass,message));
                }
            }
        };
    }

    private DescribedPredicate<JavaClass> haveAFunnyName() {
        return new DescribedPredicate<JavaClass>("have a funny name") {
            @Override
            public boolean apply(JavaClass javaClass) {
                return javaClass.getSimpleName().toUpperCase().contains("FUN");
            }
        };
    }
}
