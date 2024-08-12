package uz.pdp.fastfoodapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.fastfoodapp.model.Category;
import uz.pdp.fastfoodapp.service.CategoryService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/save")
    public ResponseEntity<?> save(Category category) {
        return ResponseEntity.ok(categoryService.save(category));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        return ResponseEntity.ok(categoryService.getByName(name));
    }
    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }
}
