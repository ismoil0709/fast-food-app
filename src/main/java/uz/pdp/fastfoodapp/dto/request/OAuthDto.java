package uz.pdp.fastfoodapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.fastfoodapp.util.annotations.Email;

@AllArgsConstructor
@Getter
public class OAuthDto {
    @Email
    private String email;
    @NotBlank
    private String name;
}
