package com.watch.store.service;

import com.watch.store.dto.ShoppingCartDto;
import com.watch.store.dto.ShoppingCartWatchDto;
import com.watch.store.entity.ChosenWatch;
import com.watch.store.entity.ShoppingCart;
import com.watch.store.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {
    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartDto getShoppingCartByUserEmail(String email) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserEmail(email);
        ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
        Double subTotal = 0.0;
        for (ChosenWatch chosenWatch : shoppingCart.getChosenWatches()) {
            ShoppingCartWatchDto shoppingCartWatchDto = ShoppingCartWatchDto.builder()
                    .name(chosenWatch.getWatch().getTitle())
                    .price(String.valueOf(chosenWatch.getWatch().getPrice()))
                    .quantity(String.valueOf(chosenWatch.getChosenQuantity())).build();
            Double auxPrice = chosenWatch.getChosenQuantity() * chosenWatch.getWatch().getPrice();

            subTotal += auxPrice;
            shoppingCartWatchDto.setTotal(String.valueOf(auxPrice));
            shoppingCartDto.add(shoppingCartWatchDto);
        }
        shoppingCartDto.setSubTotal(String.valueOf(subTotal));
        shoppingCartDto.setTotal(String.valueOf(subTotal + 50));
        return shoppingCartDto;
    }
}
