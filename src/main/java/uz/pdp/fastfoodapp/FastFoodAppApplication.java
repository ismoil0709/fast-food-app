package uz.pdp.fastfoodapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class FastFoodAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastFoodAppApplication.class, args);
    }

}
