package edu.uoc.epcsd.showcatalog;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.onionArchitecture;

@AnalyzeClasses(packages = "edu.uoc.epcsd.showcatalog")
public class OnionArchitectureTest {

    @ArchTest
    static final ArchRule onion_architecture_is_respected = onionArchitecture()
            .domainModels("edu.uoc.epcsd.showcatalog.domain..")
            .domainServices("edu.uoc.epcsd.showcatalog.domain.service..")
            .applicationServices("edu.uoc.epcsd.showcatalog.application")
            //.adapter("cli", "..adapter.cli..")
            .adapter("persistence", "edu.uoc.epcsd.showcatalog.domain.repository..")
            .adapter("rest", "edu.uoc.epcsd.showcatalog.application.rest..");
}
