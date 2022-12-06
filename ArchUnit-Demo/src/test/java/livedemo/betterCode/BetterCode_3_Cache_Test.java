package livedemo.betterCode;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.muchsoft")
public class BetterCode_3_Cache_Test {

    @ArchTest
    void meinErsterArchUnitTest(JavaClasses importedClasses) {

        ArchRule rule = classes()
                .that().resideInAPackage("..api..")
                .should().bePublic();

        rule.check(importedClasses);
    }

    @ArchTest
    public static final ArchRule IMPL_IN_BACKEND_ONLY = classes()
            .that().haveSimpleNameEndingWith("Impl")
            .should().resideInAPackage("..backend..")
            .orShould().beAnnotatedWith(Deprecated.class)
            .because("we don't want implementation details in api or frontend (see docs/adr/0001-impl-in-backend.md)");
}
