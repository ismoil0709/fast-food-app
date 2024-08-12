package uz.pdp.fastfoodapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.fastfoodapp.util.annotations.Email;

@AllArgsConstructor
@Getter
public class ValidEmailDto {
    @Email
    private String email;
    private Integer code;
}
