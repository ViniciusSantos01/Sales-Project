package io.github.ViniciusSantos01.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderInformationDTO {
    private Integer code;
    private String documentNumber;
    private String clientName;
    private BigDecimal total;
    private String orderDate;
    private String status;
    private List<ItemInformationDTO> items;
}
