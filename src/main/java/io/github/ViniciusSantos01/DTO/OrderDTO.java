package io.github.ViniciusSantos01.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * {
 *     "client" : 1,
 *     "total" : 100,
 *     "items" : [
 *         {
 *             "product": 1,
 *             "quantity": 10
 *         }
 *     ]
 * }
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Integer client;
    private BigDecimal total;
    private List<OrderItemDTO> items;


}
