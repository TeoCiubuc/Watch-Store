package com.watch.store.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ChosenWatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer chosenQuantity;
    @ManyToOne
    @JoinColumn
    private Watch watch;
    @ManyToOne
    @JoinColumn
    private ShoppingCart shoppingCart;
    @ManyToOne
    @JoinColumn
    private CustomerOrder customerOrder;
}
