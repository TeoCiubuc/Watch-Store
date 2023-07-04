package com.watch.store.service;

import com.watch.store.dto.ChosenWatchDto;
import com.watch.store.entity.ChosenWatch;
import com.watch.store.entity.User;
import com.watch.store.entity.Watch;
import com.watch.store.repository.ChosenWatchRepository;
import com.watch.store.repository.UserRepository;
import com.watch.store.repository.WatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChosenWatchService {
    @Autowired
    ChosenWatchRepository chosenWatchRepository;
    @Autowired
    WatchRepository watchRepository;
    @Autowired
    UserRepository userRepository;

    public void addToCart(ChosenWatchDto chosenWatchDto, String watchId, String email) {
        ChosenWatch chosenWatch = buildProduct(chosenWatchDto, watchId, email);
        chosenWatchRepository.save(chosenWatch);
    }

    private ChosenWatch buildProduct(ChosenWatchDto chosenWatchDto, String watchId,
                                     String email) {
        ChosenWatch chosenWatch = new ChosenWatch();
        chosenWatch.setChosenQuantity(Integer.parseInt(chosenWatchDto.getQuantity()));

        Optional<Watch> watch = watchRepository.findById(Integer.parseInt(watchId));
        watch.ifPresent(chosenWatch::setWatch);

        Optional<User> user = userRepository.findByEmail(email);
        user.ifPresent(value -> chosenWatch.setShoppingCart(value.getShoppingCart()));
        return chosenWatch;
    }
}
