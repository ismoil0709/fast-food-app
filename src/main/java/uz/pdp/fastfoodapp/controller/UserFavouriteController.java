package uz.pdp.fastfoodapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.fastfoodapp.service.UserFavouriteService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user-favourite")
public class UserFavouriteController {
    private final UserFavouriteService userFavouriteService;

    @GetMapping("/read")
    public ResponseEntity<?> getUserFavourite() {
        return ResponseEntity.ok(userFavouriteService.getUserFavourites());
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<?> add(@PathVariable(name = "id") UUID productId) {
        userFavouriteService.addFavourite(productId);
        return ResponseEntity.ok("added");
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> remove(@PathVariable(name = "id") UUID productId) {
        userFavouriteService.removeFavourite(productId);
        return ResponseEntity.ok("removed");
    }
}
