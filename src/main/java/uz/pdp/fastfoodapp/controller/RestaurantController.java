package uz.pdp.fastfoodapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.fastfoodapp.dto.request.RestaurantCrudDto;
import uz.pdp.fastfoodapp.dto.response.SuccessResponse;
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
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(restaurantService.getById(id));
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(restaurantService.getAll());
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        return ResponseEntity.ok(restaurantService.getByName(name));
    }
    @PostMapping("/setDiscount/{discount}")
    public ResponseEntity<?> setDiscount(@RequestBody RestaurantCrudDto restaurant, @PathVariable Double discount) {
        restaurantService.setDiscount(restaurant, discount);
        return ResponseEntity.ok(new SuccessResponse("Discount set successfully"));
    }
}
