package com.app.ecom.service;

import com.app.ecom.dao.ProductRepository;
import com.app.ecom.dto.ProductRequest;
import com.app.ecom.dto.ProductResponse;
import com.app.ecom.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceIMPL implements  ProductService{

    private final ProductRepository repository;

    public ProductServiceIMPL(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {

        Product product=new Product();
        updateProductFromProductRequest(product,productRequest);
        Product savedData=repository.save(product);
        return mappingProductToProductResponse(savedData);

    }

    @Override
    public Optional<ProductResponse> updateProduct(long id, ProductRequest productRequest) {
        return repository.findById(id).map(exitingProduct ->{
                    this.updateProductFromProductRequest(exitingProduct,productRequest);
                    Product updatedProduct=this.repository.save(exitingProduct);
                    return mappingProductToProductResponse(updatedProduct);
        }
                );


    }

    @Override
    public List<ProductResponse> fetchAllProducts() {
        return repository.findByActiveTrue().stream().map(this::mappingProductToProductResponse).collect(Collectors.toList());
    }

    @Override
    public boolean deleteProduct(long id) {
        return repository.findById(id).map(product -> {
            product.setActive(false);
            repository.save(product);
            return  true;
        }).orElse(false);
    }

    @Override
    public List<ProductResponse> searchProducts(String keywords) {
        return repository.searchProduct(keywords).stream().map(this::mappingProductToProductResponse).collect(Collectors.toList());
    }

    private ProductResponse mappingProductToProductResponse(Product data) {

        ProductResponse response = new ProductResponse();
        response.setId(data.getId());
        response.setName(data.getName());
        response.setDescription(data.getDescription());
        response.setPrice(data.getPrice());
        response.setStockQuantity(data.getStockQuantity());
        response.setImageURL(data.getImageURL());
        response.setActive(data.getActive());
        response.setCreatedAt(data.getCreatedAt());
        response.setUpdatedAt(data.getUpdatedAt());
        response.setCategory(data.getCategory());
        return  response ;
    }

    private void updateProductFromProductRequest(Product product, ProductRequest request) {

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setStockQuantity(request.getStockQuantity());
        product.setPrice(request.getPrice());
        product.setCategory(request.getCategory());
        product.setImageURL(request.getImageURL());
    }


}
