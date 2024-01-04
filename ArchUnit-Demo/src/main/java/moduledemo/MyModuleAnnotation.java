package moduledemo;

public @interface MyModuleAnnotation {

    String name();

    String[] allowedDependencies();

    String[] exposedPackages();

}
