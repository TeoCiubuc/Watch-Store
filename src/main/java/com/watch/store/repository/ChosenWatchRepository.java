package com.watch.store.repository;

import com.watch.store.entity.ChosenWatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChosenWatchRepository extends JpaRepository <ChosenWatch,Integer> {
}
