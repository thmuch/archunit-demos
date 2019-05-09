package livedemo.jax2019;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchRules;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.Stateful;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.muchsoft")
public class JAX_3_Cache_Test {

    @ArchTest
    public void myFirstArchUnitTest(JavaClasses javaClasses) {

        ArchRule rule = classes().that().resideInAPackage("..api..")
                .should().bePublic();

        rule.check(javaClasses);
    }

    @ArchTest
    public void noStatefulEJBs(JavaClasses javaClasses) {

        ArchRule rule = classes().should().notBeAnnotatedWith(Stateful.class);

        rule.check(javaClasses);
    }

    @ArchTest
    public static final ArchRule IMPL_SHOULD_BE_IN_BACKEND =
                classes().that().haveSimpleNameEndingWith("Impl")
                .should().resideInAPackage("..backend..")
                .orShould().beAnnotatedWith(Deprecated.class);

    @ArchTest
    public static final ArchRules TESTS_ANDERSWO = ArchRules.in(JAX_1_Test.class);

}
