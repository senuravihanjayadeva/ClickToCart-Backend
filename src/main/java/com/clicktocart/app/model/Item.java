package com.clicktocart.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "items_tbl")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String brand;
    private int ram;
    private int storage;
    private Double price;
    private String description;
    private String imgLink;
    private int stock;
    @ManyToOne
    @JoinColumn(name="sellarid")
    private User user;
    private String sellarName;

    @JsonBackReference
    public User getUser() {
        return user;
    }



}
