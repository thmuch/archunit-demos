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
import static com.tngtech.archunit.library.plantuml.PlantUmlArchCondition.Configurations.consideringOnlyDependenciesInDiagram;
import static com.tngtech.archunit.library.plantuml.PlantUmlArchCondition.adhereToPlantUmlDiagram;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.muchsoft")
public class ArchUnit_7_PlantUML_Test {

    @ArchTest
    public void plantUML(JavaClasses importedClasses) {

        String plantumlFile = "src/test/resources/plantuml-example.puml";

        ArchRule rule = classes().should(adhereToPlantUmlDiagram(plantumlFile, consideringOnlyDependenciesInDiagram()));

        rule.check(importedClasses);
    }
}
