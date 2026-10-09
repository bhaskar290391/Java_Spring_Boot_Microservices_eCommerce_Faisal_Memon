package com.app.ecom.dao;

import com.app.ecom.dto.ProductResponse;
import com.app.ecom.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByActiveTrue();

    @Query("SELECT p FROM Product p WHERE p.active = true AND p.stockQuantity > 0 AND LOWER(p.name) LIKE LOWER(CONCAT('%', :keywords, '%'))")
    List<Product> searchProduct(@Param("keywords") String keywords);



}
