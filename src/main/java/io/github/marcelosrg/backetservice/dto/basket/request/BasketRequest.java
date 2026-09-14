package io.github.marcelosrg.backetservice.dto.basket.request;

import io.github.marcelosrg.backetservice.dto.product.request.ProductRequest;

import java.util.List;

public record BasketRequest(Long clientId, List<ProductRequest> products) {
}
