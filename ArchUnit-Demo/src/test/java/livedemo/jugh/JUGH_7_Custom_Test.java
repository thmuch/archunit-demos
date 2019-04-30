package livedemo.jugh;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.*;
import org.junit.Test;

import javax.persistence.Entity;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.priority;

public class JUGH_7_Custom_Test {

    @Test
    public void funny_classes() {

        // classes that PREDICATE should CONDITION

        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.muchsoft");

        ArchRule rule = classes().that(haveAFunnyName()).should(resideInAFunnyPackage());

        rule.check(importedClasses);
    }

    private ArchCondition<JavaClass> resideInAFunnyPackage() {
        return new ArchCondition<JavaClass>("reside in a funny package") {
            @Override
            public void check(JavaClass javaClass, ConditionEvents conditionEvents) {
                if (!javaClass.getPackageName().toUpperCase().contains("FUN")) {
                    String message =
                            String.format("Class %s resides in package %s, which is not funny",
                                    javaClass.getSimpleName(),
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
