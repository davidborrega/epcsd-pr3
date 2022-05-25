package edu.uoc.epcsd.showcatalog;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.onionArchitecture;
@AnalyzeClasses(packages = "edu.uoc.epcsd.showcatalog", importOptions = ImportOption.DoNotIncludeTests.class)
public class OnionArchitectureTest {

    private static String APP_PACKAGE = "edu.uoc.epcsd.showcatalog";

    @ArchTest
    static final ArchRule onion_architecture_is_respected = onionArchitecture()
            .domainModels(APP_PACKAGE + ".domain..")
            .domainServices(APP_PACKAGE + ".domain.service..")
            .applicationServices(APP_PACKAGE + ".application..")
            .adapter("kafka", APP_PACKAGE + ".infrastructure.kafka..")
            .adapter("persistence", APP_PACKAGE + ".infrastructure.repository.jpa");
}