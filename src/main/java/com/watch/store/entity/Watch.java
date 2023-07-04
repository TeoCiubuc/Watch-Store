package com.watch.store.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Watch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private Double price;
    private Integer quantity;
    @OneToOne
    @JoinColumn (name = "file_id",referencedColumnName = "id")
    private FileCover fileCover;

    @OneToMany(mappedBy = "watch")
    private List<ChosenWatch> chosenWatches;
}
