package io.github.ViniciusSantos01.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemInformationDTO {

    private String productDescription;
    private BigDecimal unityPrice;
    private Integer quantity;

}
