package uz.pdp.fastfoodapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.fastfoodapp.model.enums.PriceRating;
import uz.pdp.fastfoodapp.util.annotations.Price;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductCrudDto {
    @NotNull
    @NotBlank
    private String name;

    private String description;

    @Price
    private Double price;

    @Price
    private Double discount;

    private List<UUID> attachmentIds;

    @NotNull
    private PriceRating priceRating;

    @NotNull
    private UUID categoryId;

    @NotNull
    private UUID restaurantId;


}
