package archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

public class ArchUnit_6_Layers_Slices_Test {

    @Test
    public void adhere_to_layered_architecture() {

        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.muchsoft");

        ArchRule rule = layeredArchitecture()
                .layer("Frontend").definedBy("..frontend..")
                .layer("API").definedBy("..api..")
                .layer("Backend").definedBy("..backend..")
                .whereLayer("Frontend").mayNotBeAccessedByAnyLayer()
                .whereLayer("API").mayOnlyBeAccessedByLayers("Frontend","Backend")
                .whereLayer("Backend").mayNotBeAccessedByAnyLayer();

        rule.check(importedClasses);
    }

    @Test
    public void no_cycles_in_slices() {

        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.muchsoft");

        ArchRule rule = slices()
                .matching("com.muchsoft.demo.(*)..")
                .should().beFreeOfCycles();

        rule.check(importedClasses);
    }

    @Test
    public void no_dependencies_between_slices() {

        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.muchsoft");

        ArchRule rule = slices()
                .matching("com.muchsoft.demo.(*)..")
                .should().notDependOnEachOther();

        rule.check(importedClasses);
    }
}
