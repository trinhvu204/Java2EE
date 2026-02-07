package com.example.Lap04.service;

import com.example.Lap04.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@Service
public class ProductService {

    private final List<Product> list = new ArrayList<>();

    public List<Product> getAll() {
        return list;
    }

    public Product get(int id) {
        return list.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void add(Product p) {
        int maxId = list.stream()
                .mapToInt(Product::getId)
                .max().orElse(0);

        p.setId(maxId + 1);
        list.add(p);
    }

    public void update(Product p) {
        Product old = get(p.getId());

        if (old != null) {
            old.setName(p.getName());
            old.setPrice(p.getPrice());
            old.setCategory(p.getCategory());

            if (p.getImage() != null)
                old.setImage(p.getImage());
        }
    }

    public void updateImage(Product product, MultipartFile file) {

        if (file == null || file.isEmpty())
            return;

        if (!file.getContentType().startsWith("image/")) {
            throw new RuntimeException("File không phải hình ảnh!");
        }

        try {
            // 👉 Upload ra thư mục ngoài project
            Path dir = Paths.get("uploads/images");

            Files.createDirectories(dir);

            String name = UUID.randomUUID()
                    + "_" + file.getOriginalFilename();

            Files.copy(file.getInputStream(),
                    dir.resolve(name),
                    StandardCopyOption.REPLACE_EXISTING);

            product.setImage(name);

        } catch (IOException e) {
            throw new RuntimeException("Lỗi upload ảnh");
        }
    }
    public void delete(int id) {
        Product p = get(id);

        if (p != null && p.getImage() != null) {
            try {
                Path imagePath = Paths.get("uploads/images", p.getImage());
                Files.deleteIfExists(imagePath);
            } catch (IOException e) {
                System.out.println("Không xóa được ảnh");
            }
        }

        list.removeIf(c -> c.getId() == id);
    }

}
