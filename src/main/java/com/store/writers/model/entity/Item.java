package com.store.writers.model.entity;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "text")
    private String description;

    private double price;
    private String category;
    private int unitsInStock;

    @Transient
    private MultipartFile image;

    private boolean active = true;

    @OneToMany(mappedBy = "item")
    private List<ItemToCartItem> itemToCartItemList;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(int unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public List<ItemToCartItem> getItemToCartItemList() {
        return itemToCartItemList;
    }

    public void setItemToCartItemList(List<ItemToCartItem> itemToCartItemList) {
        this.itemToCartItemList = itemToCartItemList;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
