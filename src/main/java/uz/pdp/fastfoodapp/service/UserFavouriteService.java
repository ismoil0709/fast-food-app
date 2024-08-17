package uz.pdp.fastfoodapp.service;

import uz.pdp.fastfoodapp.dto.response.UserFavouriteDTO;

import java.util.UUID;


public interface UserFavouriteService {
    UserFavouriteDTO getUserFavourites();

    void addFavourite(UUID productId);

    void removeFavourite(UUID productId);
}
