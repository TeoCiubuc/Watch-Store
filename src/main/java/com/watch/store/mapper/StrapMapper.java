package com.watch.store.mapper;

import com.watch.store.dto.StrapDto;
import com.watch.store.entity.Strap;
import org.springframework.stereotype.Component;

@Component
public class StrapMapper {
    public Strap strapMapper(StrapDto strapDto){
        Strap strap = new Strap();
        strap.setTitle(strapDto.getTitle());
        strap.setDescription(strapDto.getDescription());
        strap.setPrice(Double.parseDouble(strapDto.getPrice()));
        strap.setQuantity(Integer.parseInt(strapDto.getQuantity()));
        return strap;
    }
}
