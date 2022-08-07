package io.github.ViniciusSantos01.Service;

import io.github.ViniciusSantos01.DTO.OrderDTO;
import io.github.ViniciusSantos01.entity.ClientOrder;


public interface OrderService {
    ClientOrder save (OrderDTO dto);
}
