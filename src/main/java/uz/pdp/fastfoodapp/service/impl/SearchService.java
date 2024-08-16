package uz.pdp.fastfoodapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.fastfoodapp.dto.response.SearchResultDto;
import uz.pdp.fastfoodapp.exception.NotFoundException;
import uz.pdp.fastfoodapp.model.Product;
import uz.pdp.fastfoodapp.model.Restaurant;
import uz.pdp.fastfoodapp.model.enums.SearchResultType;
import uz.pdp.fastfoodapp.repo.ProductRepository;
import uz.pdp.fastfoodapp.repo.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final ProductRepository productRepository;
    private final RestaurantRepository restaurantRepository;

    public List<SearchResultDto> search(String keyword) {
        List<SearchResultDto> searchResultDto = new ArrayList<>();
        List<Product> allProduct = productRepository.findAllByName(keyword);
        List<Restaurant> allRestaurant = restaurantRepository.findAllByName(keyword);

        if (!allProduct.isEmpty()){
            for (Product product : allProduct){
                searchResultDto.add(new SearchResultDto(product.getId(), SearchResultType.PRODUCT));
            }
        }
        if (!allRestaurant.isEmpty()){
            for (Restaurant restaurant : allRestaurant){
                searchResultDto.add(new SearchResultDto(restaurant.getId(), SearchResultType.RESTAURANT));
            }
        }
        if (searchResultDto.isEmpty()){
            throw new NotFoundException("Product or restaurant");
        }
        return searchResultDto;
    }
}
