package io.github.marcelosrg.backetservice.client;

import io.github.marcelosrg.backetservice.client.response.PlatziProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "PlatziStoreClient", url = "${basketservice.client.platzi}")
public interface PlatziStoreClient {

    @GetMapping("/products")
    List<PlatziProductResponse> getAllProducts();

    @GetMapping("/products/{id}")
    PlatziProductResponse getProductById(@PathVariable Long id);

}
