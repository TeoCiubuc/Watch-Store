package com.watch.store.service;

import com.watch.store.dto.StrapDto;
import com.watch.store.entity.Strap;
import com.watch.store.mapper.StrapMapper;
import com.watch.store.repository.StrapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StrapService {
    @Autowired
    StrapRepository strapRepository;
    @Autowired
    private StrapMapper strapMapper;

    public void saveStrap(StrapDto strapDto){
        Strap strap = strapMapper.strapMapper(strapDto);
        strapRepository.save(strap);
    }
}
