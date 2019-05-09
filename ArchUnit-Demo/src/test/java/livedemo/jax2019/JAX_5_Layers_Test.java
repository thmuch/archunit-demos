package livedemo.jax2019;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

public class JAX_5_Layers_Test {

    @Test
    public void implShouldBeInBackend() {

        JavaClasses javaClasses = new ClassFileImporter().importPackages("com.muchsoft");

        ArchRule rule = layeredArchitecture()
                .layer("Frontend").definedBy("..frontend..")
                .layer("API").definedBy("..api..")
                .layer("Backend").definedBy("..backend..")
                .whereLayer("Frontend").mayNotBeAccessedByAnyLayer()
                .whereLayer("Backend").mayNotBeAccessedByAnyLayer()
                .whereLayer("API").mayOnlyBeAccessedByLayers("Frontend","Backend");

        rule.check(javaClasses);
    }
}
