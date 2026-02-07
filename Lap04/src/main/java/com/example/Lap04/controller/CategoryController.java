package com.example.Lap04.controller;

import com.example.Lap04.model.Category;
import com.example.Lap04.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")

public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        return "category/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("category", new Category());
        return "category/create";
    }

    @PostMapping("/create")
    public String create(
            @Valid Category category,
            BindingResult result) {

        if (result.hasErrors()) {
            return "category/create";
        }

        categoryService.add(category);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {

        Category category = categoryService.get(id);

        if (category == null) return "error/404";

        model.addAttribute("category", category);
        return "category/edit";
    }

    @PostMapping("/edit")
    public String edit(
            @Valid Category category,
            BindingResult result) {

        if (result.hasErrors()) {
            return "category/edit";
        }

        categoryService.update(category);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        categoryService.delete(id);
        return "redirect:/categories";
    }
}
