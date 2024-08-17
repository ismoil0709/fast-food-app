package uz.pdp.fastfoodapp.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.fastfoodapp.util.annotations.Email;
import uz.pdp.fastfoodapp.util.annotations.Password;

@AllArgsConstructor
@Getter
public class ChangePasswordDto {
    @Email
    private String email;
    @NotNull
    private Integer code;
    @Password
    private String newPassword;
}
