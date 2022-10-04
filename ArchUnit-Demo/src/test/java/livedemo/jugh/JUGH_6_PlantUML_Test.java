package livedemo.jugh;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.plantuml.rules.PlantUmlArchCondition.Configuration.consideringOnlyDependenciesInDiagram;
import static com.tngtech.archunit.library.plantuml.rules.PlantUmlArchCondition.adhereToPlantUmlDiagram;

public class JUGH_6_PlantUML_Test {

    @Test
    public void backend_does_not_access_frontend() {

        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.muchsoft");

        String plantumlFile = "src/test/resources/plantuml-example.puml";

        ArchRule rule = classes().should(adhereToPlantUmlDiagram(plantumlFile, consideringOnlyDependenciesInDiagram()));

        rule.check(importedClasses);
    }
}
