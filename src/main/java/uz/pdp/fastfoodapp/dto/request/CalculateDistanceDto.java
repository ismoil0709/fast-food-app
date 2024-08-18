package uz.pdp.fastfoodapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.fastfoodapp.model.Address;

@AllArgsConstructor
@Getter
public class CalculateDistanceDto {
    private Address userAddress;
    private Address restaurantAddress;
}
