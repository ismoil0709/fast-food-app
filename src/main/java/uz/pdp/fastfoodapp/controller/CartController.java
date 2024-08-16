package uz.pdp.fastfoodapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.fastfoodapp.dto.response.SuccessResponse;
import uz.pdp.fastfoodapp.service.CartService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;

    @PostMapping("/add/{userId}/{productId}")
    public ResponseEntity<?> addCart(@PathVariable UUID userId, @PathVariable UUID productId) {
        cartService.add(userId, productId);
        return ResponseEntity.ok(new SuccessResponse("Product successfully added to the basket"));
    }

    @DeleteMapping("/remove/{userId}/{productId}")
    public ResponseEntity<?> removeCart(@PathVariable UUID userId, @PathVariable UUID productId) {
        cartService.remove(userId, productId);
        return ResponseEntity.ok(new SuccessResponse("Product successfully removed from the basket"));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getCartById(@PathVariable UUID id) {
        return ResponseEntity.ok(cartService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCart() {
        return ResponseEntity.ok(cartService.getAll());
    }

    @DeleteMapping("/clear/id/{id}")
    public ResponseEntity<?> clearCartById(@PathVariable UUID id) {
        cartService.clearCart(id);
        return ResponseEntity.ok(new SuccessResponse("Products successfully cleared from the basket"));
    }
}
