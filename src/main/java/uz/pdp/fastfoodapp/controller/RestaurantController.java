package uz.pdp.fastfoodapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.fastfoodapp.dto.request.RestaurantCrudDto;
import uz.pdp.fastfoodapp.model.Restaurant;
import uz.pdp.fastfoodapp.service.RestaurantService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody RestaurantCrudDto crudDto) {
        return ResponseEntity.ok(restaurantService.save(crudDto));
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(restaurantService.getById(id));
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(restaurantService.getAll());
    }
    @GetMapping("/find/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        return ResponseEntity.ok(restaurantService.getByName(name));
    }
}
