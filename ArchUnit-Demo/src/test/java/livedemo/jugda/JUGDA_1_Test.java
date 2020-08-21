package livedemo.jugda;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.Priority;
import org.junit.Test;

import javax.ejb.Stateful;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.priority;

public class JUGDA_1_Test {

    @Test
    public void myFirstArchUnitTest() {

        JavaClasses classes = new ClassFileImporter().importPackages("com.muchsoft");

//        JavaClass javaClass = classes.get(OrderBean.class);
//
//        for (JavaClass clazz: classes       ) {
//            if (!clazz.getModifiers().contains(JavaModifier.PUBLIC)) {
//                // throw Exception
//            }
//        }

        ArchRule rule = priority(Priority.HIGH)
                .classes()
//                .that().resideInAPackage("..api..")
                .should().bePublic();

        rule.check(classes);
    }

    @Test
    public void no_stateful_EJBs() {

        JavaClasses classes = new ClassFileImporter().importPackages("com.muchsoft");

        ArchRule rule = classes()
                .should().notBeAnnotatedWith(Stateful.class)
//                .as("sind mir einfach zu old school");
                .because("they don't scale well");

        rule.check(classes);
    }
}
