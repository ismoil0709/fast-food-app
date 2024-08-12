package uz.pdp.fastfoodapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.fastfoodapp.exception.InvalidDataException;
import uz.pdp.fastfoodapp.exception.NotFoundException;
import uz.pdp.fastfoodapp.model.Restaurant;
import uz.pdp.fastfoodapp.repo.RestaurantRepository;
import uz.pdp.fastfoodapp.service.RestaurantService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Override
    public Restaurant getById(UUID id) {
        if (id == null) throw new InvalidDataException("ID");
        return restaurantRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Restaurant"));
    }

    @Override
    public List<Restaurant> getAll() {
        if (restaurantRepository.findAll().isEmpty()) throw new NotFoundException("Restaurants");
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant getByName(String name) {
        if (name.isEmpty() || name.isBlank()) throw new InvalidDataException("Name");
        return restaurantRepository.findByName(name).orElseThrow(() -> new NotFoundException("Restaurant"));
    }
}
