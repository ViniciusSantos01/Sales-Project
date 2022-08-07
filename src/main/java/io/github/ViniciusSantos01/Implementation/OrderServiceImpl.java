package io.github.ViniciusSantos01.Implementation;

import io.github.ViniciusSantos01.DTO.OrderDTO;
import io.github.ViniciusSantos01.DTO.OrderItemDTO;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
                .orElseThrow(() -> new RuleException("Code client invalid"));
        ClientOrder clientOrder = new ClientOrder();
        clientOrder.setTotal(dto.getTotal());
        clientOrder.setOrderDate(LocalDate.now());
        clientOrder.setClient(client);

        List<ItemClientOrder> itemsClientOrder = convertItems(clientOrder, dto.getItems());
        orders.save(clientOrder);
        orderedItemsRepository.saveAll(itemsClientOrder);
        clientOrder.setItems(itemsClientOrder);
        return clientOrder;
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
                            .orElseThrow(() -> new RuleException("Code product invalid: " + idProduct));
                    ItemClientOrder itemClientOrder = new ItemClientOrder();
                    itemClientOrder.setQuantity(dto.getQuantity());
                    itemClientOrder.setClientOrder(clientOrder);
                    itemClientOrder.setProduct(product);
                    return itemClientOrder;
                }).collect(Collectors.toList());

    }
}
