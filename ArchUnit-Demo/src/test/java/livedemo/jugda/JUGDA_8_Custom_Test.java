package livedemo.jugda;

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

public class JUGDA_8_Custom_Test {

    @Test
    public void custom_predicates_and_conditions() {

        // classes that PREDICATE should CONDITION

        JavaClasses classes = new ClassFileImporter().importPackages("com.muchsoft");

        ArchRule rule = classes()
                .that(haveAFunnyName())
                .should(resideInAFunnyPackage());

        rule.check(classes);
    }

    private ArchCondition<JavaClass> resideInAFunnyPackage() {
        return new ArchCondition<JavaClass>("reside in a funny package") {
            @Override
            public void check(JavaClass item, ConditionEvents events) {
                if (!item.getPackageName().toUpperCase().contains("FUN")) {

                    String message =
                            String.format("funny class %s resides in a package %s, which is not funny",
                                    item.getSimpleName(),
                                    item.getPackageName());

                    events.add(SimpleConditionEvent.violated(item,message));
                }
            }
        };
    }

    private DescribedPredicate<JavaClass> haveAFunnyName() {
        return new DescribedPredicate<JavaClass>("have a funny name") {
            @Override
            public boolean test(JavaClass input) {
                return input.getSimpleName().toUpperCase().contains("FUN");
            }
        };
    }
}
