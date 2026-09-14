package io.github.marcelosrg.backetservice.service;

import io.github.marcelosrg.backetservice.dto.basket.request.BasketRequest;
import io.github.marcelosrg.backetservice.entity.Basket;
import io.github.marcelosrg.backetservice.repository.BasketRepository;
import org.springframework.stereotype.Service;

@Service
public class BasketService {
    private final BasketRepository basketRepository;

    public BasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }
    public Basket createBasket(BasketRequest basketRequest) {

       Basket basket = Basket.builder().build();

       basket.calculateTotalPrice();
       return basketRepository.save(basket);
    }

}
