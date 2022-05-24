package edu.uoc.epcsd.showcatalog;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "edu.uoc.epcsd.showcatalog")
public class CatalogRepositoryIntegrationTestConfig {

}
