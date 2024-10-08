package uz.pdp.fastfoodapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.fastfoodapp.dto.request.ProductCrudDto;
import uz.pdp.fastfoodapp.service.ProductService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ProductCrudDto crudDto) {
        return ResponseEntity.ok(productService.save(crudDto));
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(productService.getById(id));
    }
    @GetMapping("/find/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        return ResponseEntity.ok(productService.getByName(name));
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }
    @GetMapping("/byCategory/{category_name}")
    public ResponseEntity<?> findByCategory(@PathVariable String category_name) {
        return ResponseEntity.ok(productService.getByCategory(category_name));
    }
    @GetMapping("/all/popular")
    public ResponseEntity<?> getPopular(){
        return ResponseEntity.ok(productService.getPopular());
    }
}
