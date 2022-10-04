package livedemo.jugh;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

public class JUGH_5_Layers_Test {

    @Test
    public void backend_does_not_access_frontend() {

        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.muchsoft");

        ArchRule rule = layeredArchitecture()
                .consideringAllDependencies()
                .layer("Frontend").definedBy("..frontend..")
                .layer("API").definedBy("..api..")
                .layer("Backend").definedBy("..backend..")
                .whereLayer("Frontend").mayNotBeAccessedByAnyLayer()
                .whereLayer("API").mayOnlyBeAccessedByLayers("Frontend","Backend")
                .whereLayer("Backend").mayNotBeAccessedByAnyLayer();

        rule.check(importedClasses);
    }
}
