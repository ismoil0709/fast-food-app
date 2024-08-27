package uz.pdp.fastfoodapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
