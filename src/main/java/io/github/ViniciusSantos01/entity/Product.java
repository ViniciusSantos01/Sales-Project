package io.github.ViniciusSantos01.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "description")
    @NotEmpty(message = "{field.description.required}")
    private String description;

    @Column(name = "UNITY_PRICE")
    @NotNull(message = "{field.price.required}")
    private BigDecimal UNITY_PRICE;

}
