package livedemo.jugostfalen;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import org.junit.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class JUGOstfalen_8_Custom_Test {

    @Test
    public void myFirstArchUnitTest() {

        JavaClasses classes = new ClassFileImporter().importPackages("com.muchsoft");

        // classes that PREDICATE should CONDITION

        ArchRule rule = classes()
                .that(haveAFunnyName())
                .should(resideInAFunnyPackage())
                .because("we want to have fun");

        rule.check(classes);
    }

    private ArchCondition<JavaClass> resideInAFunnyPackage() {
        return new ArchCondition<>("reside in a funny package") {
            @Override
            public void check(JavaClass javaClass, ConditionEvents conditionEvents) {
                if (!javaClass.getPackageName().toUpperCase().contains("FUN")) {

                    String message = String.format("Class %s resides in a package %s, which is not funny",
                            javaClass.getSimpleName(),
                            javaClass.getPackageName());

                    conditionEvents.add(SimpleConditionEvent.violated(javaClass, message));
                }
            }
        };
    }

    private DescribedPredicate<JavaClass> haveAFunnyName() {
        return new DescribedPredicate<>("have a funny name") {
            @Override
            public boolean test(JavaClass javaClass) {
                return javaClass.getSimpleName().toUpperCase().contains("FUN");
            }
        };
    }
}
