package uz.pdp.fastfoodapp.service;

import org.springframework.stereotype.Service;
import uz.pdp.fastfoodapp.model.Cart;

import java.util.List;
import java.util.UUID;

@Service
public interface CartService {
    void add(UUID userId, UUID productId);
    void remove(UUID userId, UUID productId);
    Cart getById(UUID userId);
    List<Cart> getAll();
    void clearCart(UUID id);
}
