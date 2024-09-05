package uz.pdp.fastfoodapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class AddressCrudDto {
    private Double longitude;
    private Double latitude;
    private String branch;
    private UUID userId;
}
