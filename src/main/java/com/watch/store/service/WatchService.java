package com.watch.store.service;

import com.watch.store.dto.WatchDto;
import com.watch.store.entity.Watch;
import com.watch.store.mapper.WatchMapper;
import com.watch.store.repository.WatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class WatchService {
    @Autowired
    WatchRepository watchRepository;
    @Autowired
    private WatchMapper watchMapper;

    public List<Watch> listAllWatches(){
        return watchRepository.findAll();
    }

    public void saveWatch(WatchDto watchDto, MultipartFile file) throws IOException {
    Watch watch = watchMapper.watchMapper(watchDto,file);
    watchRepository.save(watch);
    }
}
