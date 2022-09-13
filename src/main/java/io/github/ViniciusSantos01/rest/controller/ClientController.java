package io.github.ViniciusSantos01.rest.controller;

import io.github.ViniciusSantos01.Repository.Clients;
import io.github.ViniciusSantos01.entity.Client;
import io.swagger.annotations.*;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
@Api("Api Clients")
public class ClientController {

    private Clients clients;

    public ClientController(Clients clients) {
        this.clients = clients;
    }

    @GetMapping("{id}")
    @ApiOperation("Get details from one client")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Client found"),
            @ApiResponse(code = 404, message = "Client not found for this ID")
    })
    public Client getClientById(@PathVariable
                                    @ApiParam("Client ID") Integer id){

        return clients.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Save new client")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Client saved with success"),
            @ApiResponse(code = 404, message = "Validation error")
    })
    public Client save(@RequestBody @Valid Client client){
        return clients.save(client);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable Integer id){

        clients.findById(id)
                .map(client -> {
                    clients.delete(client);
                    return client;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));

    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id,
                                 @RequestBody @Valid Client client){
        clients
                .findById(id)
                .map(existClient ->{
                    client.setId(existClient.getId());
                    clients.save(client);
                    return existClient;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
    }

    @GetMapping
    public List<Client> find(Client filter){
        ExampleMatcher matcher = ExampleMatcher
                                        .matching()
                                        .withIgnoreCase()
                                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filter, matcher);
        return clients.findAll(example);

    }

}
