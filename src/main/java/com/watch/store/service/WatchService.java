package com.watch.store.service;

import com.watch.store.entity.Watch;
import com.watch.store.repository.WatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchService {
    @Autowired
    WatchRepository watchRepository;

    public List<Watch> listAllWatches(){
        return watchRepository.findAll();
    }
}
