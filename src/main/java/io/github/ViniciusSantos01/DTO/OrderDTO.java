package io.github.ViniciusSantos01.DTO;

import io.github.ViniciusSantos01.Validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
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

    @NotNull(message = "{field.client-code.required}")
    private Integer client;

    @NotNull(message = "{field.total.required}")
    private BigDecimal total;

    @NotEmptyList(message = "{field.order-items.required}")
    private List<OrderItemDTO> items;


}
