package com.watch.store.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ShoppingCartWatchDto {
    private String name;
    private String price;
    private String quantity;
    private String total;
}
