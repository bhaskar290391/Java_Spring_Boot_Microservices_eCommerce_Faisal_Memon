package com.app.ecom.controller;

import com.app.ecom.dto.ProductRequest;
import com.app.ecom.dto.ProductResponse;
import com.app.ecom.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest product) {
        return new ResponseEntity<>(service.createProduct(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable long id, @RequestBody ProductRequest product)  {
       return service.updateProduct(id, product).map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> fetchAllActiveProducts(){
        return ResponseEntity.ok(service.fetchAllProducts());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id){
        boolean response =service.deleteProduct(id);
        return  response ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProducts(@RequestParam("keyword") String keyword){
        return  ResponseEntity.ok(service.searchProducts(keyword));
    }
}
