package livedemo.gedoplan;

import archunit.ArchUnit_3_Cache_Test;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.*;
import com.tngtech.archunit.lang.ArchRule;
import jakarta.ejb.Stateful;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.muchsoft.demo", cacheMode = CacheMode.PER_CLASS)
public class GEDOPLAN_3_Cache_Test {

    @ArchTest
    void myFirstArchUnitTest(JavaClasses importedClasses) {

        ArchRule rule = classes()
                .that().resideInAPackage("..api..")
                .should().bePublic();

        rule.check(importedClasses);
    }

    @ArchTest
    public static final ArchRule NO_STATEFUL_EJBS = classes()
            .should().notBeAnnotatedWith(Stateful.class)
            .because("they don't scale well (see adr/no-stateful-ejb.md)");

    @ArchTest
    @ArchIgnore
    public static final ArchRule IMPL_IN_BACKEND = classes()
            .that().haveSimpleNameEndingWith("Impl")
            .should().resideInAPackage("..backend..")
            .orShould().beAnnotatedWith(Deprecated.class);

    @ArchTest
    public static final ArchTests TESTS_ANDERSWO = ArchTests.in(ArchUnit_3_Cache_Test.class);
}
