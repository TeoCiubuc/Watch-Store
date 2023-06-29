package com.watch.store.repository;

import com.watch.store.entity.Watch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchRepository extends JpaRepository<Watch,Integer> {
}
