package livedemo.gedoplan;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.muchsoft.demo")
public class GEDOPLAN_4_Dependencies_Test {

    @ArchTest
    public void class_hierarchy_does_not_access_other_hierarchy(JavaClasses importedClasses) {

        ArchRule rule = noClasses()
                .that().resideInAPackage("de.gedoplan.newstuff..")
                .should().dependOnClassesThat().resideInAPackage("de.gedoplan.oldstuff..");

        rule.check(importedClasses);
    }
}
