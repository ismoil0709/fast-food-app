package uz.pdp.fastfoodapp.service;

import org.springframework.stereotype.Service;
import uz.pdp.fastfoodapp.dto.response.UserFavouriteDTO;

import java.util.UUID;

@Service
public interface UserFavouriteService {
    UserFavouriteDTO getUserFavourites();
    void addFavourite(UUID productId);
    void removeFavourite(UUID productId);
}
