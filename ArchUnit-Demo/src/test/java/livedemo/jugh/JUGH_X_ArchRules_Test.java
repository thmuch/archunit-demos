package livedemo.jugh;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchRules;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.CacheMode;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.GeneralCodingRules;

//@RunWith(ArchUnitRunner.class) // necessary for JUnit 4
@AnalyzeClasses(packages = "com.muchsoft",cacheMode = CacheMode.PER_CLASS)
public class JUGH_X_ArchRules_Test {

    @ArchTest
    public static final ArchRules ARCHITECTUR_TESTS = ArchRules.in(JUGH_3_Cache_Test.class);

    @ArchTest
    public static final ArchRules CODE_CONVENTIONS = ArchRules.in(JUGH_2_Test.class);

    @ArchTest
    public static final ArchRule NO_STANDARD_STREAMS = GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;
}
