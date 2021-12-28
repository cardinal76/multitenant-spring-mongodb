package com.multitenant.app;

import com.multitenant.entity.Planet;
import com.multitenant.entity.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@ComponentScan(basePackages = "com")

public class MultiTenantApplication {
    public static void main(String[] args) {
        SpringApplication.run(MultiTenantApplication.class, args);
    }

    @Autowired
    private PlanetRepository planetRepository;

/*@PostConstruct
void insert(){
    Planet pla = new Planet();
    pla.setName("marte");
    planetRepository.save(pla);

}*/


}
