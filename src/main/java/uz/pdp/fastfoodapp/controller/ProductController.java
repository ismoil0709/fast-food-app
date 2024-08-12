package uz.pdp.fastfoodapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.fastfoodapp.model.Product;
import uz.pdp.fastfoodapp.service.ProductService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<?> save(Product product) {
        return ResponseEntity.ok(productService.save(product));
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(productService.getById(id));
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name) {
        return ResponseEntity.ok(productService.getByName(name));
    }
    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(productService.getAll());
    }
    @GetMapping("/byCategory/{category_name}")
    public ResponseEntity<?> findByCategory(@PathVariable String category_name) {
        return ResponseEntity.ok(productService.getByCategory(category_name));
    }
}
