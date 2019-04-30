package extensions;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.base.Optional;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaField;
import com.tngtech.archunit.core.domain.JavaMember;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.lang.annotation.Annotation;
import java.util.Calendar;
import java.util.Date;

public class Predicates {

	public static DescribedPredicate<JavaClass> doNotHaveSingleTableInheritance() {
		return new DescribedPredicate<JavaClass>( "no SINGLE_TABLE inheritance" ) {
			@Override
			public boolean apply(JavaClass input) {
				for (JavaClass sc : input.getAllSuperClasses()) {
					Optional<Inheritance> inheritance = sc.tryGetAnnotationOfType( Inheritance.class );
					if (inheritance.isPresent()) {
						if (inheritance.get().strategy() == InheritanceType.SINGLE_TABLE) {
							return false;
						}
					}
				}
				return true;
			}
		};
	}

	public static DescribedPredicate<JavaField> areTemporalFields() {
		return new DescribedPredicate<JavaField>( "are temporal fields (Date or Calendar)" ) {
			@Override
			public boolean apply(JavaField input) {
				return input.getType().isAssignableTo( Date.class ) || input.getType().isAssignableTo( Calendar.class );
			}
		};
	}

	public static DescribedPredicate<JavaField> areTemporalTimestampFields() {
		return new DescribedPredicate<JavaField>( "are timestamps (are annotated with temporal timestamp)" ) {
			@Override
			public boolean apply(JavaField input) {
				Optional<Temporal> temporal = input.tryGetAnnotationOfType( Temporal.class );
				return (temporal.isPresent() && temporal.get().value() == TemporalType.TIMESTAMP);
			}
		};
	}

	public static DescribedPredicate<JavaField> areTemporalDateFields() {
		return new DescribedPredicate<JavaField>( "are dates (are annotated with temporal date)" ) {
			@Override
			public boolean apply(JavaField input) {
				Optional<Temporal> temporal = input.tryGetAnnotationOfType( Temporal.class );
				return temporal.isPresent() && temporal.get().value() == TemporalType.DATE;
			}
		};
	}

	public static DescribedPredicate<JavaField> areTemporalTimeFields() {
		return new DescribedPredicate<JavaField>( "are times (are annotated with temporal time)" ) {
			@Override
			public boolean apply(JavaField input) {
				Optional<Temporal> temporal = input.tryGetAnnotationOfType( Temporal.class );
				return temporal.isPresent() && temporal.get().value() == TemporalType.TIME;
			}
		};
	}

	public static DescribedPredicate<JavaField> belongToAClassAnnotatedWith(Class<? extends Annotation> annotationClass) {
		return new DescribedPredicate<JavaField>(
				"belong to a class annotated with @" + annotationClass.getSimpleName() ) {
			@Override
			public boolean apply(JavaField input) {
				return input.getOwner().tryGetAnnotationOfType( annotationClass ).isPresent();
			}
		};
	}

	public static DescribedPredicate<JavaMember> areAnnotatedWith(Class<? extends Annotation> annotationClass) {
		return new DescribedPredicate<JavaMember>( "are annotated with @" + annotationClass.getSimpleName() ) {
			@Override
			public boolean apply(JavaMember input) {
				return input.isAnnotatedWith( annotationClass );
			}
		};
	}
}
