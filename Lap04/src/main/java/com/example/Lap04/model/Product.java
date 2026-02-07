package com.example.Lap04.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;


public class Product {
    private int id;
    @NotBlank(message = "Ten san pham khong duoc de trong")
    private String name;
    @Length(min = 0,max = 200, message = "Ten hinh khong duoc qua 200 ky tu")
    private String image;
    @NotNull(message = "Gia san pham khong duoc bo trong")
    @Min(value = 1, message = "Gia san pham khong duoc nho hon 1")
    @Max(value = 999999,message = "Gia san pham khong duoc lon hon 999999")
    private Long price;
    private Category category;
//    public void setCategory(Category category) {
//        this.category = category;
//    }
//
//    public Category getCategory() {
//        return category;
//    }

    public Product(int id, String name, String image, Long price, Category category) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.category = category;
    }

    public Product() {
        this.category = new Category();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
