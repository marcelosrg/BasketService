package io.github.marcelosrg.backetservice.controller;

import io.github.marcelosrg.backetservice.dto.basket.request.BasketRequest;
import io.github.marcelosrg.backetservice.entity.Basket;
import io.github.marcelosrg.backetservice.service.BasketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basket")
public class BasketController {

    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @PostMapping
    public ResponseEntity<Basket> createBasket(@RequestBody BasketRequest basketRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(basketService.createBasket(basketRequest));

    }
}
