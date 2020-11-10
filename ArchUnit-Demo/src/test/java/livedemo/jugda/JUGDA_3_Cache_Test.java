package livedemo.jugda;

import archunit.ArchUnit_3_Cache_Test;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchRules;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.Priority;

import javax.ejb.Stateful;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.priority;

//@RunWith(ArchUnitRunner.class) // necessary for JUnit 4
@AnalyzeClasses(packages = "com.muchsoft")
public class JUGDA_3_Cache_Test {

    @ArchTest
    public void myFirstArchUnitTest(JavaClasses classes) {

        ArchRule rule = priority(Priority.HIGH)
                .classes()
                .that().resideInAPackage("..api..")
                .should().bePublic();

        rule.check(classes);
    }

    @ArchTest
    public static ArchRule NO_STATEFUL_EJBS = classes()
            .should().notBeAnnotatedWith(Stateful.class)
            .because("they don't scale well");


    @ArchTest
    public static ArchRule IMPL_IN_BACKEND = classes()
            .that().haveNameMatching(".*Impl")
            .should().resideInAPackage("..backend..")
            .orShould().beAnnotatedWith(Deprecated.class);

    @ArchTest
    public static ArchRules TESTS_ANDERSWO = ArchRules.in(ArchUnit_3_Cache_Test.class);
}
