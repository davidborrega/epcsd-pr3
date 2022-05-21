package edu.uoc.epcsd.showcatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories
//@EnableJpaRepositories(basePackages = "edu.uoc.epcsd.showcatalog.infrastructure.repository.jpa")
public class ShowCatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShowCatalogApplication.class, args);
    }

}
