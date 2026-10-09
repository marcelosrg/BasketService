package io.github.marcelosrg.backetservice.service;

import io.github.marcelosrg.backetservice.dto.basket.request.BasketRequest;
import io.github.marcelosrg.backetservice.dto.payment.PaymentRequest;
import io.github.marcelosrg.backetservice.dto.product.response.PlatziProductResponse;
import io.github.marcelosrg.backetservice.entity.Basket;
import io.github.marcelosrg.backetservice.entity.Product;
import io.github.marcelosrg.backetservice.entity.Status;
import io.github.marcelosrg.backetservice.exception.NotFoundException;
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

    public Basket getBasketById(String id) {

        return basketRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Basket não encontrada")
        );

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


    public Basket updateBasket (String id, BasketRequest basketRequest) {
        Basket basket = basketRepository.findById(id).orElseThrow( () -> new IllegalStateException("Basket not found"));

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

        basket.setProducts(products);
        basket.calculateTotalPrice();
        return basketRepository.save(basket);

    }

    public Basket payBasket (String id, PaymentRequest paymentRequest) {
        Basket basket = basketRepository.findById(id).orElseThrow( () -> new IllegalStateException("Basket not found"));
        basket.setPaymentMethod(paymentRequest.paymentMethod());
        basket.setStatus(Status.SOLD);
        return basketRepository.save(basket);

    }

    public void deleteBasket(String basketId) {
        getBasket(basketId);
        basketRepository.deleteById(basketId);
    }

    private Basket getBasket(String basketId) {
        return basketRepository.findById(basketId).orElseThrow(() -> new NotFoundException("Basket not found"));
    }

    private List<Product> manageProductsRequest(BasketRequest basketRequest) {
        List<Product> products = new ArrayList<>();
        basketRequest.products().forEach(productRequest -> {
            PlatziProductResponse platziProductReponse = productService.getProductById(productRequest.id());
            products.add(Product.builder()
                    .id(platziProductReponse.id())
                    .price(platziProductReponse.price())
                    .title(platziProductReponse.title())
                    .quantity(productRequest.quantity())
                    .build());

        });
        return products;
    }
}
