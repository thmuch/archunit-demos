package archunit;

import com.muchsoft.demo.user.api.UserService;
import com.muchsoft.demo.user.api.model.User;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;

import javax.persistence.EntityManager;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class ArchUnit_5_Inheritance_Test {

    @Test
    public void user_interface_is_implemented_with_convenient_name() {

        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.muchsoft");

        ArchRule rule = classes()
                .that().implement(UserService.class)
                .should().haveSimpleNameEndingWith("UserServiceImpl");

        rule.check(importedClasses);
    }

    @Test
    public void user_implementations_are_only_used_in_user_module() {

        JavaClasses importedClasses = new ClassFileImporter().importClasspath();

        ArchRule rule = classes()
                .that().areAssignableTo(User.class)
                .should().onlyHaveDependentClassesThat().resideInAnyPackage("..user..");

        rule.check(importedClasses);
    }
}
