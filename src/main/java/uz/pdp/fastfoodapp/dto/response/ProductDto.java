package uz.pdp.fastfoodapp.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.fastfoodapp.model.Category;
import uz.pdp.fastfoodapp.model.Product;
import uz.pdp.fastfoodapp.model.enums.PriceRating;
import uz.pdp.fastfoodapp.util.annotations.Price;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class ProductDto {
    private String name;
    private String description;
    @Price
    private Double price;
    @Price
    private Double discount;
    private PriceRating priceRating;
    private Category category;
    private UUID restaurantId;

    public ProductDto(Product product) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.discount = product.getDiscount();
        this.priceRating = product.getPriceRating();
        this.category = product.getCategory();
        this.restaurantId = product.getRestaurantId();
    }
}
