package livedemo.jugh;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;

import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

public class JUGH_5_Slices_Test {

    @Test
    public void slices_should_be_free_of_cycles() {

        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.muchsoft");

        ArchRule rule = slices()
                .matching("com.muchsoft.demo.(*)..").should().beFreeOfCycles();

        rule.check(importedClasses);
    }

    @Test
    public void slices_should_not_depend_on_each_other() {

        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.muchsoft");

        ArchRule rule = slices()
                .matching("com.muchsoft.demo.(*)..").should().notDependOnEachOther();

        rule.check(importedClasses);
    }
}
