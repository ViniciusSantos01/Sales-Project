package io.github.ViniciusSantos01.rest.controller;

import io.github.ViniciusSantos01.DTO.OrderDTO;
import io.github.ViniciusSantos01.Service.OrderService;
import io.github.ViniciusSantos01.entity.ClientOrder;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody OrderDTO dto){
        ClientOrder clientOrder = service.save(dto);
        return clientOrder.getId();
    }
}
