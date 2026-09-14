package io.github.marcelosrg.backetservice.service;

import io.github.marcelosrg.backetservice.client.PlatziStoreClient;
import io.github.marcelosrg.backetservice.client.response.PlatziProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final PlatziStoreClient platziStoreClient;

    public ProductService(PlatziStoreClient platziStoreClient) {
        this.platziStoreClient = platziStoreClient;
    }


    public List<PlatziProductResponse> getAllProducts(){
        return platziStoreClient.getAllProducts();
    }

    public PlatziProductResponse getProductById(Long id){
        return platziStoreClient.getProductById(id);
    }

}
