package uz.pdp.fastfoodapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserFavouriteDTO {
    private List<UUID> favouriteProductIds;
}
