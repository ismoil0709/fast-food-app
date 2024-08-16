package uz.pdp.fastfoodapp.service.impl;

import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.pdp.fastfoodapp.dto.response.UserFavouriteDTO;
import uz.pdp.fastfoodapp.model.Product;
import uz.pdp.fastfoodapp.model.User;
import uz.pdp.fastfoodapp.model.UserFavourite;
import uz.pdp.fastfoodapp.repo.ProductRepository;
import uz.pdp.fastfoodapp.repo.UserFavouriteRepository;
import uz.pdp.fastfoodapp.repo.UserRepository;
import uz.pdp.fastfoodapp.service.UserFavouriteService;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

@EnableScheduling
@RequiredArgsConstructor
public class UserFavouriteServiceImpl implements UserFavouriteService {
    private final UserFavouriteRepository userFavouriteRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ConcurrentMap<User, List<Product>> addedFavourite = new ConcurrentHashMap<>();
    private final ConcurrentMap<User, List<Product>> removedFavourite = new ConcurrentHashMap<>();

    @Override
    public UserFavouriteDTO getUserFavourites() {
        User currentUser = getCurrentUser();
        Set<Product> favouriteProducts = new HashSet<>();

        userFavouriteRepository.findByUser(currentUser)
                .ifPresent(userFavourite -> favouriteProducts.addAll(userFavourite.getFavouriteProducts()));

        favouriteProducts.addAll(addedFavourite.getOrDefault(currentUser, Collections.emptyList()));
        favouriteProducts.removeAll(removedFavourite.getOrDefault(currentUser, Collections.emptyList()));

        UserFavouriteDTO userFavouriteDTO = new UserFavouriteDTO(new ArrayList<>());
        userFavouriteDTO.getFavouriteProductIds().addAll(
                favouriteProducts.stream().map(Product::getId).toList()
        );

        return userFavouriteDTO;
    }


    @Override
    public void addFavourite(UUID productId) {
        Product product = productRepository.getById(productId);
        User currentUser = getCurrentUser();

        removedFavourite.computeIfPresent(currentUser, (user, products) -> {
            products.remove(product);
            return products.isEmpty() ? null : products;
        });

        addedFavourite.computeIfAbsent(currentUser, k -> new ArrayList<>()).add(product);
    }

    @Override
    public void removeFavourite(UUID productId) {
        Product product = productRepository.getById(productId);
        User currentUser = getCurrentUser();

        addedFavourite.computeIfPresent(currentUser, (user, products) -> {
            products.remove(product);
            return products.isEmpty() ? null : products;
        });

        removedFavourite.computeIfAbsent(currentUser, k -> new ArrayList<>()).add(product);
    }


    private User getCurrentUser() {
        UUID userId = UUID.fromString(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        return userRepository.findById(userId).orElseThrow();
    }

    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.MINUTES)
    private void sendToDatabase() {
        for (User user : addedFavourite.keySet()) {
            List<Product> products = addedFavourite.get(user);
            Optional<UserFavourite> optionalUser = userFavouriteRepository.findByUser(user);
            if (optionalUser.isEmpty()) {
                UserFavourite userFavourite = new UserFavourite(null, user, products);
                userFavouriteRepository.save(userFavourite);
                continue;
            }
            UserFavourite userFavourite = optionalUser.get();
            userFavourite.getFavouriteProducts().addAll(products);
            userFavouriteRepository.save(userFavourite);
        }
        addedFavourite.clear();

        for (User user : removedFavourite.keySet()) {
            List<Product> products = removedFavourite.get(user);
            Optional<UserFavourite> optionalUser = userFavouriteRepository.findByUser(user);
            if (optionalUser.isEmpty()) {
                continue;
            }
            UserFavourite userFavourite = optionalUser.get();
            userFavourite.getFavouriteProducts().removeAll(products);
            userFavouriteRepository.save(userFavourite);
        }
        removedFavourite.clear();
    }

    @PreDestroy
    private void preDestroy() {
        sendToDatabase();
    }
}
