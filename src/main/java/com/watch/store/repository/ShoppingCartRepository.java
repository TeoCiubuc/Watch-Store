package com.watch.store.repository;

import com.watch.store.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
    public ShoppingCart findByUserEmail(String email);
}
