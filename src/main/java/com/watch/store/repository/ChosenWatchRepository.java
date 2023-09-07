package com.watch.store.repository;

import com.watch.store.entity.ChosenWatch;
import com.watch.store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChosenWatchRepository extends JpaRepository <ChosenWatch,Integer> {
    public ChosenWatch findByWatchId(int watchId);
    List<ChosenWatch> findAllByWatchId(int watchId);
}
