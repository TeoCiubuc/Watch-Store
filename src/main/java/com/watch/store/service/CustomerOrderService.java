package com.watch.store.service;

import com.watch.store.entity.ChosenWatch;
import com.watch.store.entity.CustomerOrder;
import com.watch.store.entity.ShoppingCart;
import com.watch.store.entity.User;
import com.watch.store.repository.ChosenWatchRepository;
import com.watch.store.repository.CustomerOrderRepository;
import com.watch.store.repository.ShoppingCartRepository;
import com.watch.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerOrderService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CustomerOrderRepository customerOrderRepository;
    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Autowired
    ChosenWatchRepository chosenWatchRepository;

    public int addCustomerOrder(String email, String shippingAddress) {
        Optional<User> user = userRepository.findByEmail(email);
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setUser(user.get());
        customerOrder.setShippingAddress(shippingAddress);
        customerOrderRepository.save(customerOrder);
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserEmail(email);
        for (ChosenWatch chosenWatch : shoppingCart.getChosenWatches()){
            chosenWatch.setShoppingCart(null);
            chosenWatch.setCustomerOrder(customerOrder);
            chosenWatchRepository.save(chosenWatch);
        }
        return customerOrder.getCustomerOrderId();
    }

    public CustomerOrder getCustomerOrder(Integer id){
        CustomerOrder customerOrder = customerOrderRepository.getById(id);
        return customerOrder;
    }
}
