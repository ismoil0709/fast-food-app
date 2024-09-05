package uz.pdp.fastfoodapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.fastfoodapp.dto.request.DailyDiscountDto;
import uz.pdp.fastfoodapp.exception.NotFoundException;
import uz.pdp.fastfoodapp.model.DailyDiscount;
import uz.pdp.fastfoodapp.model.Product;
import uz.pdp.fastfoodapp.model.Restaurant;
import uz.pdp.fastfoodapp.model.enums.Type;
import uz.pdp.fastfoodapp.repo.DailyDiscountRepository;
import uz.pdp.fastfoodapp.repo.ProductRepository;
import uz.pdp.fastfoodapp.repo.RestaurantRepository;
import uz.pdp.fastfoodapp.service.DailyDiscountService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyDiscountServiceImpl implements DailyDiscountService {
    private final RestaurantRepository restaurantRepository;
    private final ProductRepository productRepository;
    private final DailyDiscountRepository dailyDiscountRepository;

    @Override
    public void update(List<DailyDiscountDto> dailyDiscountDtos) {
        for (DailyDiscountDto dto : dailyDiscountDtos) {
            DailyDiscount dailyDiscount = toEntity(dto);

            if (dailyDiscount.getType() == Type.RESTAURANT) {
                Restaurant restaurant = restaurantRepository.findById(dailyDiscount.getRestaurantId())
                        .orElseThrow(() -> new NotFoundException("Restaurant"));

                restaurant.setDiscount(dailyDiscount.getDiscount());
                restaurantRepository.save(restaurant);

                List<Product> products = productRepository.findAllByRestaurantId(restaurant.getId());
                for (Product product : products) {
                    product.setDiscount(dailyDiscount.getDiscount());
                    productRepository.save(product);
                }

                dailyDiscountRepository.save(dailyDiscount);
            } else if (dailyDiscount.getType() == Type.PRODUCT) {
                Product product = productRepository.findById(dailyDiscount.getProductId())
                        .orElseThrow(() -> new NotFoundException("Product"));

                product.setDiscount(dailyDiscount.getDiscount());
                productRepository.save(product);
                dailyDiscountRepository.save(dailyDiscount);
            }
        }
    }

    private DailyDiscount toEntity(DailyDiscountDto dto) {
        return DailyDiscount.builder()
                .restaurantId(dto.getRestaurantId())
                .productId(dto.getProductId())
                .discount(dto.getDiscount())
                .type(dto.getType())
                .build();
    }
}

