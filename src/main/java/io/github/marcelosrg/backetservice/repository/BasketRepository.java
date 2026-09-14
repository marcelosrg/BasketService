package io.github.marcelosrg.backetservice.repository;

import io.github.marcelosrg.backetservice.entity.Basket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BasketRepository extends MongoRepository<Basket,String> {

}
