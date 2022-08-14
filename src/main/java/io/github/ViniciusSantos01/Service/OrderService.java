package io.github.ViniciusSantos01.Service;

import io.github.ViniciusSantos01.DTO.OrderDTO;
import io.github.ViniciusSantos01.entity.ClientOrder;
import io.github.ViniciusSantos01.enums.OrderStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;


public interface OrderService {
    ClientOrder save (OrderDTO dto);

    Optional<ClientOrder> loadCompleteOrder(@Param("id") Integer id);

    void updateStatus(Integer id, OrderStatus orderStatus);
}
