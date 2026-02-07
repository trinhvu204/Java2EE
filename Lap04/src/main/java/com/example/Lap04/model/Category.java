package com.example.Lap04.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


public class Category {
    private Integer id;

    @NotBlank(message = "Ten danh muc khong duoc de trong")
    private String name;

    public Category() {}

    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}