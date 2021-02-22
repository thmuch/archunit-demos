package extensions;

import com.tngtech.archunit.base.Optional;
import com.tngtech.archunit.core.domain.JavaField;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import jakarta.persistence.*;

import java.util.regex.Pattern;

public class Conditions {

    public static ArchCondition<JavaField> haveANameEndingWith(final String suffix) {
        return new ArchCondition<JavaField>("have a name ending with \"" + suffix + "\"") {
            @Override
            public void check(JavaField field, ConditionEvents events) {
                boolean satisfied = field.getName().endsWith(suffix);
                String message = String.format("Field %s.%s has a name%s ending with \"" + suffix + "\"",
                        field.getOwner().getName(), field.getName(), satisfied ? "" : " not");
                events.add(new SimpleConditionEvent(field, satisfied, message));
            }
        };
    }

    public static ArchCondition<JavaField> haveANameNotEndingWith(final String suffix) {
        return new ArchCondition<JavaField>("have a name not ending with \"" + suffix + "\"") {
            @Override
            public void check(JavaField field, ConditionEvents events) {
                boolean satisfied = !field.getName().endsWith(suffix);
                String message = String.format("Field %s.%s has a name%s ending with \"" + suffix + "\"",
                        field.getOwner().getName(), field.getName(), satisfied ? " not" : "");
                events.add(new SimpleConditionEvent(field, satisfied, message));
            }
        };
    }

    public static ArchCondition<JavaField> haveADbColumnNameEndingWith(final String suffix) {
        return new ArchCondition<JavaField>("have a DB column name ending with \"" + suffix + "\"") {
            @Override
            public void check(JavaField field, ConditionEvents events) {
                Optional<Column> column = field.tryGetAnnotationOfType(Column.class);
                boolean satisfied = column.isPresent() && column.get().name().endsWith(suffix);
                String message = String.format("Field %s.%s has a DB column name%s ending with \"" + suffix + "\"",
                        field.getOwner().getName(), field.getName(), satisfied ? "" : " not");
                events.add(new SimpleConditionEvent(field, satisfied, message));
            }
        };
    }

    public static ArchCondition<JavaField> haveADbColumnNameNotEndingWith(final String suffix) {
        return new ArchCondition<JavaField>("have a DB column name not ending with \"" + suffix + "\"") {
            @Override
            public void check(JavaField field, ConditionEvents events) {
                Optional<Column> column = field.tryGetAnnotationOfType(Column.class);
                boolean satisfied = !column.isPresent() || !column.get().name().endsWith(suffix);
                String message = String.format("Field %s.%s has a DB column name%s ending with \"" + suffix + "\"",
                        field.getOwner().getName(), field.getName(), satisfied ? " not" : "");
                events.add(new SimpleConditionEvent(field, satisfied, message));
            }
        };
    }

    public static ArchCondition<JavaField> beAnnotatedWithEagerFetching() {
        return new ArchCondition<JavaField>("be annotated with \"(fetch = EAGER)\"") {
            @Override
            public void check(JavaField field, ConditionEvents events) {
                Optional<FetchType> fetchType = getFetchType(field);
                boolean satisfied = fetchType.isPresent() && (fetchType.get() == FetchType.EAGER);
                String message = String.format("Field %s.%s is%s annotated with \"fetch = EAGER)\"",
                        field.getOwner().getName(), field.getName(), satisfied ? "" : " not");
                events.add(new SimpleConditionEvent(field, satisfied, message));
            }

            private Optional<FetchType> getFetchType(JavaField field) {
                Optional<OneToMany> oneToMany = field.tryGetAnnotationOfType(OneToMany.class);
                if (oneToMany.isPresent()) {
                    return Optional.of(oneToMany.get().fetch());
                }
                Optional<ManyToOne> manyToOne = field.tryGetAnnotationOfType(ManyToOne.class);
                if (manyToOne.isPresent()) {
                    return Optional.of(manyToOne.get().fetch());
                }
                Optional<ManyToMany> manyToMany = field.tryGetAnnotationOfType(ManyToMany.class);
                if (manyToMany.isPresent()) {
                    return Optional.of(manyToMany.get().fetch());
                }
                Optional<OneToOne> oneToOne = field.tryGetAnnotationOfType(OneToOne.class);
                if (oneToOne.isPresent()) {
                    return Optional.of(oneToOne.get().fetch());
                }
                Optional<ElementCollection> elementCollection = field.tryGetAnnotationOfType(ElementCollection.class);
                if (elementCollection.isPresent()) {
                    return Optional.of(elementCollection.get().fetch());
                }
                return Optional.absent();
            }
        };
    }

    public static ArchCondition<JavaMethod> beAccessedFromTestClassesOnly() {
        return new ArchCondition<JavaMethod>("be accessed only from test classes") {
            @Override
            public void check(JavaMethod method, ConditionEvents events) {

                final String methodName = method.getName();
                final String methodClassName = method.getOwner().getName();

                method.getAccessesToSelf().forEach(access -> {

                    final String accessingClass = access.getOriginOwner().getName();

                    final boolean satisfied = accessingClass.endsWith("Test")
                            || accessingClass.equals(methodClassName);

                    final String message = String.format("Method %s.%s is accessed from %s.%s", methodClassName,
                            methodName, accessingClass, access.getOrigin().getName());

                    events.add(new SimpleConditionEvent(method, satisfied, message));
                });
            }
        };
    }

    public static ArchCondition<JavaMethod> haveNameNotMatching(final String regex) {
        final Pattern pattern = Pattern.compile(regex);
        return new ArchCondition<JavaMethod>("have name not matching \"" + regex + "\"") {
            @Override
            public void check(JavaMethod method, ConditionEvents events) {

                final String methodName = method.getName();

                final boolean satisfied = !pattern.matcher(methodName).matches();

                String message = String.format("Method %s.%s has a name%s matching \"" + regex + "\"",
                        method.getOwner().getName(), method.getName(), satisfied ? " not" : "");
                events.add(new SimpleConditionEvent(method, satisfied, message));
            }
        };
    }
}
