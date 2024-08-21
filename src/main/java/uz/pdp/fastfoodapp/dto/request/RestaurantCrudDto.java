package uz.pdp.fastfoodapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.fastfoodapp.model.enums.PriceRating;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RestaurantCrudDto {

    @NotBlank
    private String name;

    private List<UUID> addressIds;

    private List<UUID> productIds;

    private Float rating;

    @NotNull
    private PriceRating priceRating;

    private List<UUID> attachmentIds;
    private Double discount;
}
