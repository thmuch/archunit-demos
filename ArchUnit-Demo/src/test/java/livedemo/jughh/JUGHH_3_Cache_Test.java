package livedemo.jughh;

import archunit.ArchUnit_3_Cache_Test;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchIgnore;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchTests;
import com.tngtech.archunit.lang.ArchRule;
import jakarta.persistence.Entity;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

//@RunWith(ArchUnitRunner.class) // für JUnit 4
@AnalyzeClasses(packages = "com.muchsoft.demo")
public class JUGHH_3_Cache_Test {

    @ArchTest
    void myFirstArchUnitTest(JavaClasses importedClasses) {

        ArchRule rule = classes()
                .that().resideInAPackage("..api..")
                .should().bePublic();

        rule.check(importedClasses);
    }

    @ArchTest
    static final ArchRule no_entities_in_api = classes()
            .that().resideInAPackage("..api..")
            .should().notBeAnnotatedWith(Entity.class);

    @ArchTest
    @ArchIgnore
    static final ArchRule impl_in_backend_only = classes()
            .that().haveNameMatching(".*Impl")
            .should().resideInAPackage("..backend..")
            .orShould().beAnnotatedWith(Deprecated.class);

    @ArchTest
    static final ArchTests anderswo_definierte_tests = ArchTests.in(ArchUnit_3_Cache_Test.class);
}
