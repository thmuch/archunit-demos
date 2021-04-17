package archunit;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaPackage;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.library.metrics.*;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

//@RunWith(ArchUnitRunner.class) // necessary for JUnit 4
@AnalyzeClasses(packages = "com.muchsoft")
public class ArchUnit_11_Metrics_Test {

    @ArchTest
    public void check_cumulative_dependency_metrics(JavaClasses importedClasses) {

        Set<JavaPackage> packages = importedClasses.getPackage("com.muchsoft").getSubpackages();

        MetricsComponents<JavaClass> components = MetricsComponents.fromPackages(packages);

        LakosMetrics metrics = ArchitectureMetrics.lakosMetrics(components);

//        System.out.println("CCD: " + metrics.getCumulativeComponentDependency());
//        System.out.println("ACD: " + metrics.getAverageComponentDependency());
//        System.out.println("RACD: " + metrics.getRelativeAverageComponentDependency());
//        System.out.println("NCCD: " + metrics.getNormalizedCumulativeComponentDependency());

        assertThat(metrics.getAverageComponentDependency()).isLessThan(2.0);
    }

    @ArchTest
    public void check_component_dependency_metrics(JavaClasses importedClasses) {

        Set<JavaPackage> packages = importedClasses.getPackage("com.muchsoft.demo").getSubpackages();

        MetricsComponents<JavaClass> components = MetricsComponents.fromPackages(packages);

        ComponentDependencyMetrics metrics = ArchitectureMetrics.componentDependencyMetrics(components);

//        System.out.println("Ce: " + metrics.getEfferentCoupling("com.muchsoft.demo.order"));
//        System.out.println("Ca: " + metrics.getAfferentCoupling("com.muchsoft.demo.order"));
//        System.out.println("I: " + metrics.getInstability("com.muchsoft.demo.order"));
//        System.out.println("A: " + metrics.getAbstractness("com.muchsoft.demo.order"));
//        System.out.println("D: " + metrics.getNormalizedDistanceFromMainSequence("com.muchsoft.demo.order"));

        assertThat(metrics.getInstability("com.muchsoft.demo.order")).isLessThanOrEqualTo(1.0);
    }

    @ArchTest
    public void check_visibility_metrics(JavaClasses importedClasses) {

        Set<JavaPackage> packages = importedClasses.getPackage("com.muchsoft.demo").getSubpackages();

        MetricsComponents<JavaClass> components = MetricsComponents.fromPackages(packages);

        VisibilityMetrics metrics = ArchitectureMetrics.visibilityMetrics(components);

//        System.out.println("RV : " + metrics.getRelativeVisibility("com.muchsoft.demo.order"));
//        System.out.println("ARV: " + metrics.getAverageRelativeVisibility());
//        System.out.println("GRV: " + metrics.getGlobalRelativeVisibility());

        assertThat(metrics.getRelativeVisibility("com.muchsoft.demo.order")).isLessThanOrEqualTo(1.0);
    }
}
