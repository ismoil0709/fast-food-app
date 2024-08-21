package uz.pdp.fastfoodapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.fastfoodapp.util.annotations.Email;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class ProfileUpdateDto {
    private UUID id;
    @NotBlank
    private String name;
    @Email
    private String email;
}
