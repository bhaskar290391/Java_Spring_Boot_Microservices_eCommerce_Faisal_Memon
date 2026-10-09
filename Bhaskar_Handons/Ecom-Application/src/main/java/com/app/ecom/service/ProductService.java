package com.app.ecom.service;

import com.app.ecom.dto.ProductRequest;
import com.app.ecom.dto.ProductResponse;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public ProductResponse createProduct(ProductRequest productRequest);

    public Optional<ProductResponse> updateProduct(long id, ProductRequest productRequest);

    public List<ProductResponse> fetchAllProducts();

    public boolean deleteProduct(long id);

    public List<ProductResponse> searchProducts(String keywords);
}
