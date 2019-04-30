package extensions;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaField;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.lang.AbstractClassesTransformer;
import com.tngtech.archunit.lang.ClassesTransformer;

import java.util.HashSet;
import java.util.Set;

public class ClassTransformers {

	public static ClassesTransformer<JavaField> fields() {
		return new AbstractClassesTransformer<JavaField>( "fields" ) {
			@Override
			public Iterable<JavaField> doTransform(JavaClasses collection) {
				Set<JavaField> result = new HashSet<>();
				for (JavaClass javaClass : collection) {
					result.addAll( javaClass.getFields() );
				}
				return result;
			}
		};
	}

	public static ClassesTransformer<JavaMethod> methods() {
		return new AbstractClassesTransformer<JavaMethod>( "methods" ) {
			@Override
			public Iterable<JavaMethod> doTransform(JavaClasses collection) {
				Set<JavaMethod> result = new HashSet<>();
				for (JavaClass javaClass : collection) {
					result.addAll( javaClass.getMethods() );
				}
				return result;
			}
		};
	}
}
