package edu.uoc.epcsd.showcatalog;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "edu.uoc.epcsd.showcatalog")
public class NamingConventionTest {
    @ArchTest
    static ArchRule services_should_be_suffixed_with_impl =
            classes()
                    .that().resideInAPackage("edu.uoc.epcsd.showcatalog.domain.service")
                    .and().areAnnotatedWith(Service.class)
                    .should().haveSimpleNameEndingWith("Impl");
}
