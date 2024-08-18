package uz.pdp.fastfoodapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class OrderCrudDto {
    private UUID userId;
    private List<UUID> productIds;
}
