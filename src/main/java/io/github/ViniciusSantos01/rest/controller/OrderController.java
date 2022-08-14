package io.github.ViniciusSantos01.rest.controller;

import io.github.ViniciusSantos01.DTO.ItemInformationDTO;
import io.github.ViniciusSantos01.DTO.OrderDTO;
import io.github.ViniciusSantos01.DTO.OrderInformationDTO;
import io.github.ViniciusSantos01.DTO.UpdateOrderStatusDTO;
import io.github.ViniciusSantos01.Service.OrderService;
import io.github.ViniciusSantos01.entity.ClientOrder;
import io.github.ViniciusSantos01.entity.ItemClientOrder;
import io.github.ViniciusSantos01.enums.OrderStatus;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


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

    @GetMapping("{id}")
    public OrderInformationDTO getById(@PathVariable Integer id){
        return service.loadCompleteOrder(id)
                .map( o -> convert(o))
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, " Order not found."));
    }

    @PatchMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateStatus(@PathVariable Integer id, @RequestBody UpdateOrderStatusDTO dto){

        String newStatus = dto.getNewStatus();
        service.updateStatus(id, OrderStatus.valueOf(newStatus));

    }

    private OrderInformationDTO convert(ClientOrder clientOrder){
        return OrderInformationDTO
                .builder()
                .code(clientOrder.getId())
                .orderDate(clientOrder.getOrderDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .documentNumber(clientOrder.getClient().getDocumentNumber())
                .clientName(clientOrder.getClient().getName())
                .total(clientOrder.getTotal())
                .status(clientOrder.getStatus().name())
                .items(convert(clientOrder.getItems()))
                .build();
    }

    private List<ItemInformationDTO> convert(List<ItemClientOrder> items){
        if(CollectionUtils.isEmpty(items)){
            return Collections.emptyList();
        }
        return items.stream().map( item -> ItemInformationDTO.builder()
                .productDescription(item.getProduct().getDescription())
                .unityPrice(item.getProduct().getUNITY_PRICE())
                .quantity(item.getQuantity())
                .build()
        ).collect(Collectors.toList());

    }

}
