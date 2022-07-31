package io.github.ViniciusSantos01;

import io.github.ViniciusSantos01.Repository.Clients;
import io.github.ViniciusSantos01.entity.Client;
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

            List<Client> allClients = clients.findAll();
            allClients.forEach(System.out::println);

            allClients.forEach(c -> {
                c.setName(c.getName() + " updated");
                clients.save(c);
            });

            System.out.println("Buscando Cliente");
            clients.findByName("Vin").forEach(System.out::println);

            System.out.println("Procurando..");
            allClients = clients.findAll();
            allClients.forEach(System.out::println);

            boolean exist = clients.existsByName("Vinicius updated");
            System.out.println(exist);
        };
    }
    public static void main(String[] args) {
       SpringApplication.run(SalesApplication.class, args);
    }
}
