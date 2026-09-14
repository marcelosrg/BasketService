package io.github.marcelosrg.backetservice.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {

    private Long id;
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
