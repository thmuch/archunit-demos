package livedemo.jughh;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.Architectures.onionArchitecture;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@AnalyzeClasses(packages = "com.muchsoft.demo")
public class JUGHH_6_Architectures_Test {

    @ArchTest
    void conforms_to_layered_architecture(JavaClasses importedClasses) {

        ArchRule rule = layeredArchitecture()
                .layer("Backend").definedBy("..backend..")
                .layer("API").definedBy("..api..")
                .layer("Frontend").definedBy("..frontend")
                .whereLayer("Frontend").mayNotBeAccessedByAnyLayer()
                .whereLayer("API").mayOnlyBeAccessedByLayers("Frontend", "Backend")
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
    public void conforms_to_onion_architecture(JavaClasses importedClasses) {

        ArchRule rule = onionArchitecture()
                .domainModels("oniondemo.domain.model..")
                .domainServices("oniondemo.domain.service..")
                .applicationServices("oniondemo.application..")
                .adapter("DB", "oniondemo.adapter.persistence..")
                .adapter("Web", "oniondemo.adapter.web");

        rule.check(importedClasses);
    }
}
