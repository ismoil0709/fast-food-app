package uz.pdp.fastfoodapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.fastfoodapp.util.annotations.Email;
import uz.pdp.fastfoodapp.util.annotations.Password;

@AllArgsConstructor
@Getter
public class UserLoginDto {
    @Email
    private String email;
    @Password
    private String password;
}
