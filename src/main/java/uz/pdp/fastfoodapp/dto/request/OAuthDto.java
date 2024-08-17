package uz.pdp.fastfoodapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.fastfoodapp.util.annotations.Email;

@AllArgsConstructor
@Getter
public class OAuthDto {
    @Email
    private String email;
    @NotNull
    @NotBlank
    private String name;
}
