package io.github.marcelosrg.backetservice.service;

import io.github.marcelosrg.backetservice.dto.basket.request.BasketRequest;
import io.github.marcelosrg.backetservice.dto.product.response.PlatziProductResponse;
import io.github.marcelosrg.backetservice.entity.Basket;
import io.github.marcelosrg.backetservice.entity.Product;
import io.github.marcelosrg.backetservice.entity.Status;
import io.github.marcelosrg.backetservice.repository.BasketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasketService {
    private final BasketRepository basketRepository;
    private final ProductService productService;

    public BasketService(BasketRepository basketRepository, ProductService productService) {
        this.basketRepository = basketRepository;
        this.productService = productService;
    }
    public Basket createBasket(BasketRequest basketRequest) {

        basketRepository.findByClientAndStatus(basketRequest.clientId(), Status.OPEN)
                .ifPresent(status -> {
                    throw new IllegalStateException("Basket already exists");
                });

        List<Product> products = new ArrayList<>();
        basketRequest.products().forEach(productRequest -> {
            PlatziProductResponse platziProductResponse =productService.getProductById(productRequest.id());
            products.add(Product.builder()
                    .id(platziProductResponse.id())
                    .title(platziProductResponse.title())
                    .price(platziProductResponse.price())
                    .quantity(productRequest.quantity())
                    .build());

        });

       Basket basket = Basket.builder()
               .client(basketRequest.clientId())
               .status(Status.OPEN)
               .products(products)
               .build();

       basket.calculateTotalPrice();
       return basketRepository.save(basket);
    }

}
