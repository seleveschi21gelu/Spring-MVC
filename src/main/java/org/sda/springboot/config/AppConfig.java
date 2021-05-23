package org.sda.springboot.config;

import org.sda.springboot.beans.Bean3;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.Entity;

@SpringBootApplication
@ComponentScan("org.sda.springboot") // de aici va incepe scanarea claselor aplicatiei
@EntityScan("org.sda.springboot.entities")  // folosim linia asta pt a face legatura cu db si entities, trebuie sa ii spunem noi explicit pt ca AppConfig este in subpackage

@EnableJpaRepositories("org.sda.springboot.repositories")
@Import({WebConfig.class })
public class AppConfig {
    @Bean  // se foloseste pentru a instantia si a-i spune springului sa memoreze instanta unei clase
    // pentru a o injecta in alte clase prin @Autowired
    public Bean3 createBean3() {

        return new Bean3();
    }


}
