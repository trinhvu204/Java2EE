package com.example.Lap05.controller;

import com.example.Lap05.model.Product;
import com.example.Lap05.service.CategoryService;
import com.example.Lap05.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
    public String list(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "product/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {

        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());

        return "product/add";
    }

    @PostMapping("/add")
    public String addProduct(
            @Valid @ModelAttribute Product product,
            BindingResult result,
            @RequestParam("imageFile") MultipartFile file,
            Model model) throws Exception {

        if (result.hasErrors()) {

            model.addAttribute("categories", categoryService.getAllCategories());
            return "product/add";
        }

        if (!file.isEmpty()) {

            String uploadDir = "static/images/";

            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = file.getOriginalFilename();

            Path filePath = uploadPath.resolve(fileName);

            Files.write(filePath, file.getBytes());

            product.setImage(fileName);
        }

        productService.saveProduct(product);

        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {

        Product product = productService.getProductById(id);

        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories());

        return "product/edit";
    }

    @PostMapping("/edit/{id}")
    public String editProduct(
            @PathVariable Long id,
            @Valid @ModelAttribute Product product,
            BindingResult result,
            @RequestParam("imageFile") MultipartFile file,
            Model model) throws Exception {

        if (result.hasErrors()) {

            model.addAttribute("categories", categoryService.getAllCategories());
            return "product/edit";
        }

        Product oldProduct = productService.getProductById(id);

        if (!file.isEmpty()) {

            String uploadDir = "static/images/";

            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = file.getOriginalFilename();

            Path filePath = uploadPath.resolve(fileName);

            Files.write(filePath, file.getBytes());

            product.setImage(fileName);

        } else {

            product.setImage(oldProduct.getImage());

        }

        product.setId(id);

        productService.saveProduct(product);

        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        productService.deleteProduct(id);

        return "redirect:/products";
    }
}
