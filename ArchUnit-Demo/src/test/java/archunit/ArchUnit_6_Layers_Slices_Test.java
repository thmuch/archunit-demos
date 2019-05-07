package archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.muchsoft")
public class ArchUnit_6_Layers_Slices_Test {

    @ArchTest
    public void adhere_to_layered_architecture(JavaClasses importedClasses) {

        ArchRule rule = layeredArchitecture()
                .layer("Frontend").definedBy("..frontend..")
                .layer("API").definedBy("..api..")
                .layer("Backend").definedBy("..backend..")
                .whereLayer("Frontend").mayNotBeAccessedByAnyLayer()
                .whereLayer("API").mayOnlyBeAccessedByLayers("Frontend","Backend")
                .whereLayer("Backend").mayNotBeAccessedByAnyLayer();

        rule.check(importedClasses);
    }

    @ArchTest
    public void no_cycles_in_slices(JavaClasses importedClasses) {

        ArchRule rule = slices()
                .matching("com.muchsoft.demo.(*)..")
                .should().beFreeOfCycles();

        rule.check(importedClasses);
    }

    @ArchTest
    public void no_dependencies_between_slices(JavaClasses importedClasses) {

        ArchRule rule = slices()
                .matching("com.muchsoft.demo.(*)..")
                .should().notDependOnEachOther();

        rule.check(importedClasses);
    }
}
