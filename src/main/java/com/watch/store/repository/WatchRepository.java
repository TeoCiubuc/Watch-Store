package com.watch.store.repository;

import com.watch.store.entity.Watch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WatchRepository extends JpaRepository<Watch,Integer> {
    public Watch findByTitle(String title);

    public Optional<Watch> findById(Integer id);

}
