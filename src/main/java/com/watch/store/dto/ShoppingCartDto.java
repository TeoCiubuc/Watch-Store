package com.watch.store.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter

public class ShoppingCartDto {
    private String subTotal;
    private String total;
    List<ShoppingCartWatchDto> watches = new ArrayList<>();

    public void add(ShoppingCartWatchDto shoppingCartWatchDto){
        watches.add(shoppingCartWatchDto);
    }
}
