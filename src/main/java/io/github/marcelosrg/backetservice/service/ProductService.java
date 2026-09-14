package io.github.marcelosrg.backetservice.service;

import io.github.marcelosrg.backetservice.client.PlatziStoreClient;
import io.github.marcelosrg.backetservice.client.response.PlatziProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductService {

    private final PlatziStoreClient platziStoreClient;

    public ProductService(PlatziStoreClient platziStoreClient) {
        this.platziStoreClient = platziStoreClient;
    }

    @Cacheable(value = "products")
    public List<PlatziProductResponse> getAllProducts(){
        log.info("Getting all products");
        return platziStoreClient.getAllProducts();
    }


    @Cacheable(value = "product", key = "#productId")
    public PlatziProductResponse getProductById(Long productId){
        log.info("Getting product by id {}", productId);
        return platziStoreClient.getProductById(productId);
    }

}
