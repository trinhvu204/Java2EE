package com.example.Lap04.service;

import com.example.Lap04.model.Category;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {

    private final List<Category> list = new ArrayList<>();

    public CategoryService() {
        list.add(new Category(1, "Điện thoại"));
        list.add(new Category(2, "Máy tính bảng"));
        list.add(new Category(3, "Laptop"));
    }

    public List<Category> getAll() {
        return list;
    }

    public Category get(int id) {
        return list.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }
    public void add(Category c) {
        int maxId = list.stream()
                .mapToInt(Category::getId)
                .max()
                .orElse(0);

        c.setId(maxId + 1);
        list.add(c);
    }

    public void update(Category c) {
        Category old = get(c.getId());

        if (old != null) {
            old.setName(c.getName());
        }
    }

    public void delete(int id) {
        list.removeIf(c -> c.getId() == id);
    }
}
