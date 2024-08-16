package uz.pdp.fastfoodapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.fastfoodapp.exception.NotFoundException;
import uz.pdp.fastfoodapp.model.Cart;
import uz.pdp.fastfoodapp.model.Product;
import uz.pdp.fastfoodapp.repo.CartRepository;
import uz.pdp.fastfoodapp.repo.ProductRepository;
import uz.pdp.fastfoodapp.service.CartService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Override
    public void add(UUID userId, UUID productId) {
        Cart cart = cartRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));

        cart.getProducts().add(product);
    }

    @Override
    public void remove(UUID userId, UUID productId) {
        Cart cart = cartRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));

        cart.getProducts().remove(product);
    }

    @Override
    public Cart getById(UUID userId) {
        return cartRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Cart"));
    }

    @Override
    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    @Override
    public void clearCart(UUID id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cart"));
        cart.getProducts().clear();
    }
}
