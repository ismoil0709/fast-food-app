package uz.pdp.fastfoodapp.service;

import org.springframework.stereotype.Service;
import uz.pdp.fastfoodapp.model.Restaurant;

import java.util.List;
import java.util.UUID;

@Service
public interface RestaurantService {
    Restaurant getById(UUID id);
    List<Restaurant> getAll();
    Restaurant getByName(String name);

}
