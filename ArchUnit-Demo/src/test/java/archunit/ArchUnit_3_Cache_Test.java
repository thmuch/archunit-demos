package archunit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import jakarta.ejb.Stateful;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

//@RunWith(ArchUnitRunner.class) // necessary for JUnit 4
@AnalyzeClasses(packages = "com.muchsoft")
public class ArchUnit_3_Cache_Test {

    @ArchTest
    public static final ArchRule PUBLIC_CLASSES = classes().should().bePublic();

    @ArchTest
    public static final ArchRule IMPL_IN_BACKEND = classes().that().haveNameMatching(".*Impl")
            .should().resideInAPackage("..backend..")
            .orShould().beAnnotatedWith(Deprecated.class);

    @ArchTest
    public static final ArchRule NO_STATEFUL_EJB = noClasses().should().beAnnotatedWith(Stateful.class);

}
