package archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import moduledemo.MyModuleAnnotation;

import static com.tngtech.archunit.library.modules.syntax.ModuleDependencyScope.consideringOnlyDependenciesBetweenModules;
import static com.tngtech.archunit.library.modules.syntax.ModuleRuleDefinition.modules;

//@RunWith(ArchUnitRunner.class) // necessary for JUnit 4
@AnalyzeClasses(packages = "moduledemo")
public class ArchUnit_6b_Module_Test {

    @ArchTest
    public void no_cycles(JavaClasses importedClasses) {

        ArchRule rule = modules()
                .definedByPackages("..moduledemo.(*)..")
                .should()
                .beFreeOfCycles();

        rule.check(importedClasses);
    }

    @ArchTest
    public void allowed_dependencies_only(JavaClasses importedClasses) {

        ArchRule rule = modules()
                .definedByAnnotation(MyModuleAnnotation.class)
                .should()
                .respectTheirAllowedDependenciesDeclaredIn("allowedDependencies", consideringOnlyDependenciesBetweenModules())
                .andShould()
                .onlyDependOnEachOtherThroughPackagesDeclaredIn("exposedPackages");

        rule.check(importedClasses);
    }
}
