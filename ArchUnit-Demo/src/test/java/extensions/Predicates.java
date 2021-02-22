package extensions;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.base.Optional;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaField;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Calendar;
import java.util.Date;

public class Predicates {

    public static DescribedPredicate<JavaClass> doNotHaveSingleTableInheritance() {
        return new DescribedPredicate<JavaClass>("no SINGLE_TABLE inheritance") {
            @Override
            public boolean apply(JavaClass input) {
                for (JavaClass sc : input.getAllSuperClasses()) {
                    Optional<Inheritance> inheritance = sc.tryGetAnnotationOfType(Inheritance.class);
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
        return new DescribedPredicate<JavaField>("are temporal fields (Date or Calendar)") {
            @Override
            public boolean apply(JavaField input) {
                return input.getRawType().isAssignableTo(Date.class) || input.getRawType().isAssignableTo(Calendar.class);
            }
        };
    }

    public static DescribedPredicate<JavaField> areTemporalTimestampFields() {
        return new DescribedPredicate<JavaField>("are timestamps (are annotated with temporal timestamp)") {
            @Override
            public boolean apply(JavaField input) {
                Optional<Temporal> temporal = input.tryGetAnnotationOfType(Temporal.class);
                return (temporal.isPresent() && temporal.get().value() == TemporalType.TIMESTAMP);
            }
        };
    }

    public static DescribedPredicate<JavaField> areTemporalDateFields() {
        return new DescribedPredicate<JavaField>("are dates (are annotated with temporal date)") {
            @Override
            public boolean apply(JavaField input) {
                Optional<Temporal> temporal = input.tryGetAnnotationOfType(Temporal.class);
                return temporal.isPresent() && temporal.get().value() == TemporalType.DATE;
            }
        };
    }

    public static DescribedPredicate<JavaField> areTemporalTimeFields() {
        return new DescribedPredicate<JavaField>("are times (are annotated with temporal time)") {
            @Override
            public boolean apply(JavaField input) {
                Optional<Temporal> temporal = input.tryGetAnnotationOfType(Temporal.class);
                return temporal.isPresent() && temporal.get().value() == TemporalType.TIME;
            }
        };
    }
}
