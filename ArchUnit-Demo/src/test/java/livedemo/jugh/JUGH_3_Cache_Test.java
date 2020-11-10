package livedemo.jugh;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.CacheMode;
import com.tngtech.archunit.lang.ArchRule;

import javax.ejb.Stateful;
import javax.persistence.Entity;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

//@RunWith(ArchUnitRunner.class) // necessary for JUnit 4
@AnalyzeClasses(packages = "com.muchsoft",cacheMode = CacheMode.PER_CLASS,
        importOptions = ImportOption.Predefined.DoNotIncludeTests.class)
public class JUGH_3_Cache_Test {

    @ArchTest
    public static final ArchRule NO_STATEFUL_EJB = noClasses().should().beAnnotatedWith(Stateful.class);

    @ArchTest
    public static final ArchRule IMPL_IN_BACKEND = classes()
            .that().haveNameMatching(".*Impl")
            .should().resideInAPackage("..backend..")
            .orShould().beAnnotatedWith(Deprecated.class);

    @ArchTest
    public static final ArchRule ENTITY_IN_MODEL = classes().that().areAnnotatedWith(Entity.class).should().resideInAPackage("..model..");

//    @Test
//    public void no_stateful_ejb() {
//
//        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.muchsoft");
//
//        ArchRule rule = noClasses().should().beAnnotatedWith(Stateful.class);
//
//        rule.check(importedClasses);
//    }
//
//    @Test
//    public void impl_in_backend() {
//
//        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.muchsoft");
//
//        ArchRule rule = priority(Priority.LOW).classes()
//                .that().haveNameMatching(".*Impl")
//                .should().resideInAPackage("..backend..")
//                .orShould().beAnnotatedWith(Deprecated.class);
//
//        rule.check(importedClasses);
//    }
//
//    @Test
//    public void entity_in_model() {
//
//        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.muchsoft");
//
//        ArchRule rule = classes().that().areAnnotatedWith(Entity.class).should().resideInAPackage("..model..");
//
//        rule.check(importedClasses);
//    }
}
