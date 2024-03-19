package mingu.spring.springbootr2dbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;

@EnableR2dbcAuditing
@SpringBootApplication
public class SpringbootR2dbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootR2dbcApplication.class, args);
    }

}
