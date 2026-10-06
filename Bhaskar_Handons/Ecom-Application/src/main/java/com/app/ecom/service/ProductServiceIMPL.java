package com.app.ecom.service;

import com.app.ecom.dao.ProductRepository;
import com.app.ecom.dto.ProductRequest;
import com.app.ecom.dto.ProductResponse;
import com.app.ecom.entity.Product;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
