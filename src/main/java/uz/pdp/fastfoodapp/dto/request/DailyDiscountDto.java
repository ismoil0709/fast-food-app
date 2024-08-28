package uz.pdp.fastfoodapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.fastfoodapp.model.enums.Type;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class DailyDiscountDto {
    private UUID restaurantId;
    private UUID productId;
    private Double discount;
    private Type type;
}
