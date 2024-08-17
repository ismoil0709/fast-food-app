package uz.pdp.fastfoodapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.fastfoodapp.util.annotations.Email;
import uz.pdp.fastfoodapp.util.annotations.Password;

@AllArgsConstructor
@Getter
public class UserRegisterDto {
    @NotNull
    @NotBlank
    private String name;
    @Email
    private String email;
    @Password
    private String password;
}
