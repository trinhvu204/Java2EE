package com.example.Lap04.controller;

import com.example.Lap04.model.Category;
import com.example.Lap04.model.Product;
import com.example.Lap04.service.CategoryService;
import com.example.Lap04.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService,
                             CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("listproduct", productService.getAll());
        return "product/products";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Product product = new Product();
        product.setCategory(new Category());

        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAll());
        return "product/create";
    }

    @PostMapping("/create")
    public String create(
            @Valid Product newProduct,
            BindingResult result,
            @RequestParam("category.id") Integer categoryId,
            @RequestParam("imageProduct") MultipartFile imageProduct,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAll());
            return "product/create";
        }

        productService.updateImage(newProduct, imageProduct);

        Category category = categoryService.get(categoryId);
        newProduct.setCategory(category);

        productService.add(newProduct);

        return "redirect:/products";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {

        Product product = productService.get(id);

        if (product == null) return "error/404";

        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAll());

        return "product/edit";
    }

    @PostMapping("/edit")
    public String edit(
            @Valid @ModelAttribute Product editProduct,
            BindingResult result,
            @RequestParam("imageProduct") MultipartFile imageProduct,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAll());
            return "product/edit";
        }

        try {
            Category category =
                    categoryService.get(editProduct.getCategory().getId());
            editProduct.setCategory(category);

            productService.updateImage(editProduct, imageProduct);
            productService.update(editProduct);

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("categories", categoryService.getAll());
            return "product/edit";
        }

        return "redirect:/products";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        productService.delete(id);
        return "redirect:/products";
    }
}
