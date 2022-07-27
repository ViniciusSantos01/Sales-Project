package io.github.ViniciusSantos01;

import io.github.ViniciusSantos01.Repository.Clients;
import io.github.ViniciusSantos01.domain.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
public class SalesApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clients clients){
        return args -> {
            clients.save(new Client("Vinicius"));
            clients.save(new Client("Maria"));

            List<Client> allClients = clients.getAll();
            allClients.forEach(System.out::println);

            allClients.forEach(c -> {
                c.setName(c.getName() + " updated");
                clients.update(c);
            });

            clients.searchByName("Vin").forEach(System.out::println);

            allClients = clients.getAll();
            allClients.forEach(System.out::println);
        };
    }
    public static void main(String[] args) {
       SpringApplication.run(SalesApplication.class, args);
    }
}
