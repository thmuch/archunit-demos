package extensions;

import com.tngtech.archunit.core.domain.*;
import com.tngtech.archunit.lang.AbstractClassesTransformer;
import com.tngtech.archunit.lang.ClassesTransformer;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class ClassTransformers {

    /**
     * @return
     * @deprecated Use ArchRuleDefinition.fields()/noFields() instead (since ArchUnit 0.10)
     */
    @Deprecated
    public static ClassesTransformer<JavaField> fields() {
        return new AbstractClassesTransformer<JavaField>("fields") {
            @Override
            public Iterable<JavaField> doTransform(JavaClasses collection) {
                Set<JavaField> result = new HashSet<>();
                for (JavaClass javaClass : collection) {
                    result.addAll(javaClass.getFields());
                }
                return result;
            }
        };
    }

    /**
     * @return
     * @deprecated Use ArchRuleDefinition.methods()/noMethods() instead (since ArchUnit 0.10)
     */
    @Deprecated
    public static ClassesTransformer<JavaMethod> methods() {
        return new AbstractClassesTransformer<JavaMethod>("methods") {
            @Override
            public Iterable<JavaMethod> doTransform(JavaClasses collection) {
                Set<JavaMethod> result = new HashSet<>();
                for (JavaClass javaClass : collection) {
                    result.addAll(javaClass.getMethods());
                }
                return result;
            }
        };
    }

    /**
     * Starting point for custom predicates (and conditions) about static blocks.
     *
     * @return
     */
    public static ClassesTransformer<JavaStaticInitializer> staticInitializers() {
        return new AbstractClassesTransformer<JavaStaticInitializer>("static initializers") {
            @Override
            public Iterable<JavaStaticInitializer> doTransform(JavaClasses collection) {
                return collection.stream()
                        .map(JavaClass::getStaticInitializer)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(toSet());
            }
        };
    }
}
