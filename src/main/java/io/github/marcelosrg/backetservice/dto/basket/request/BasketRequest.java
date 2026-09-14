package io.github.marcelosrg.backetservice.dto.basket.request;

public record BasketRequest(Long clientId, List<ProductRequest> products) {
}
