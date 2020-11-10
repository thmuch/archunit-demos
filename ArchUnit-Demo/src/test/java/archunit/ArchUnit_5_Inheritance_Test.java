package archunit;

import com.muchsoft.demo.user.api.UserService;
import com.muchsoft.demo.user.api.model.User;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

//@RunWith(ArchUnitRunner.class) // necessary for JUnit 4
@AnalyzeClasses(packages = "com.muchsoft")
public class ArchUnit_5_Inheritance_Test {

    @ArchTest
    public void user_interface_is_implemented_with_convenient_name(JavaClasses importedClasses) {

        ArchRule rule = classes()
                .that().implement(UserService.class)
                .should().haveSimpleNameEndingWith("UserServiceImpl");

        rule.check(importedClasses);
    }

    @ArchTest
    public void user_implementations_are_only_used_in_user_module(JavaClasses importedClasses) {

        ArchRule rule = classes()
                .that().areAssignableTo(User.class)
                .should().onlyHaveDependentClassesThat().resideInAnyPackage("..user..");

        rule.check(importedClasses);
    }
}
