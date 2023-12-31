package com.watch.store.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WatchDto {
    private String title;
    private String price;
    private String description;
    private String quantity;
    private String image;

}
