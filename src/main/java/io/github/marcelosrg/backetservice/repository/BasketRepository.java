package io.github.marcelosrg.backetservice.repository;

import io.github.marcelosrg.backetservice.entity.Basket;
import io.github.marcelosrg.backetservice.entity.Status;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BasketRepository extends MongoRepository<Basket,String> {

    Optional<Basket> findByClientAndStatus(Long client, Status status);

}
