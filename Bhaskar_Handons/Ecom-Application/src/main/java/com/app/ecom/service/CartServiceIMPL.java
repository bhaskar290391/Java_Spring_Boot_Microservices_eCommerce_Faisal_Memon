package com.app.ecom.service;

import com.app.ecom.dao.CartItemRepository;
import com.app.ecom.dao.ProductRepository;
import com.app.ecom.dao.UserRepository;
import com.app.ecom.dto.CartItemRequest;
import com.app.ecom.entity.CartItem;
import com.app.ecom.entity.Product;
import com.app.ecom.entity.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CartServiceIMPL implements CartService{

    private final ProductRepository productRepo;
    private final UserRepository userRepo;
    private final CartItemRepository cartRepo;

    public CartServiceIMPL(ProductRepository productRepo, UserRepository userRepo, CartItemRepository cartRepo) {
        this.productRepo = productRepo;
        this.userRepo = userRepo;
        this.cartRepo = cartRepo;
    }


    @Override
    public boolean addToCart(String userid, CartItemRequest cartItem) {

        Optional<Product> productOpt= productRepo.findById(cartItem.getProductId());
        if(productOpt.isEmpty())
            return false;

        Product product= productOpt.get();

        if(product.getStockQuantity() < cartItem.getQuantity())
            return false;

        Optional<User> userOpt=userRepo.findById(Long.valueOf(userid));

        if(userOpt.isEmpty())
            return false;

        User user= userOpt.get();


        CartItem existingItem=cartRepo.findByUserAndProduct(user,product);

        if(existingItem !=null){
            existingItem.setQuantity(existingItem.getQuantity()+ cartItem.getQuantity());
            existingItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(existingItem.getQuantity())));
            cartRepo.save(existingItem);

        }else {
            CartItem item =new CartItem();
            item.setUser(user);
            item.setProduct(product);
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(product.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
            cartRepo.save(item);
        }

        return true;
    }
}
