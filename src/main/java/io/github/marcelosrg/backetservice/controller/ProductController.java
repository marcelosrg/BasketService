package io.github.marcelosrg.backetservice.controller;

import io.github.marcelosrg.backetservice.client.response.PlatziProductResponse;
import io.github.marcelosrg.backetservice.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<PlatziProductResponse>> getAllProducts() {

      return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<PlatziProductResponse> getProductById(@PathVariable Long productId) {


        return ResponseEntity.ok(productService.getProductById(productId));
    }
}
