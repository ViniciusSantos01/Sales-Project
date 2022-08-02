package io.github.ViniciusSantos01;

import io.github.ViniciusSantos01.Repository.Clients;
import io.github.ViniciusSantos01.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class SalesApplication {

    public static void main(String[] args) {
       SpringApplication.run(SalesApplication.class, args);
    }
}
