package io.github.ViniciusSantos01.Implementation;

import io.github.ViniciusSantos01.DTO.OrderDTO;
import io.github.ViniciusSantos01.DTO.OrderItemDTO;
import io.github.ViniciusSantos01.Exception.OrderNotFoundException;
import io.github.ViniciusSantos01.Exception.RuleException;
import io.github.ViniciusSantos01.Repository.Clients;
import io.github.ViniciusSantos01.Repository.OrderedItems;
import io.github.ViniciusSantos01.Repository.Orders;
import io.github.ViniciusSantos01.Repository.Products;
import io.github.ViniciusSantos01.Service.OrderService;
import io.github.ViniciusSantos01.entity.Client;
import io.github.ViniciusSantos01.entity.ClientOrder;

import io.github.ViniciusSantos01.entity.ItemClientOrder;
import io.github.ViniciusSantos01.entity.Product;
import io.github.ViniciusSantos01.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.lang.Integer;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final Orders  orders;
    private final Clients clientsRepository;
    private final Products productsRepository;
    private final OrderedItems orderedItemsRepository;

    @Override
    @Transactional
    public ClientOrder save(OrderDTO dto ) {
        Integer idClient = dto.getClient();
        Client client = clientsRepository.
                findById(idClient)
                .orElseThrow(() -> new RuleException("Client code invalid"));
        ClientOrder clientOrder = new ClientOrder();
        clientOrder.setTotal(dto.getTotal());
        clientOrder.setOrderDate(LocalDate.now());
        clientOrder.setClient(client);
        clientOrder.setStatus(OrderStatus.CREATED);

        List<ItemClientOrder> itemsClientOrder = convertItems(clientOrder, dto.getItems());
        orders.save(clientOrder);
        orderedItemsRepository.saveAll(itemsClientOrder);
        clientOrder.setItems(itemsClientOrder);
        return clientOrder;
    }

    @Override
    public Optional<ClientOrder> loadCompleteOrder(Integer id) {
        return orders.findByIdFetchItems(id);
    }

    @Override
    @Transactional
    public void updateStatus(Integer id, OrderStatus orderStatus) {
        orders.findById(id)
                .map(or -> {
                    or.setStatus(orderStatus);
                    return orders.save(or);
                }).orElseThrow(() -> new OrderNotFoundException());
    }

    private List<ItemClientOrder> convertItems (ClientOrder clientOrder, List<OrderItemDTO> items){
        if (items.isEmpty()){
            throw new RuleException("It is not possible to do an order without items");
        }
        return items
                .stream()
                .map(dto -> {
                    Integer idProduct = dto.getProduct();
                    Product product = productsRepository.
                            findById(idProduct)
                            .orElseThrow(() -> new RuleException("Product code invalid: " + idProduct));
                    ItemClientOrder itemClientOrder = new ItemClientOrder();
                    itemClientOrder.setQuantity(dto.getQuantity());
                    itemClientOrder.setClientOrder(clientOrder);
                    itemClientOrder.setProduct(product);
                    return itemClientOrder;
                }).collect(Collectors.toList());

    }
}
