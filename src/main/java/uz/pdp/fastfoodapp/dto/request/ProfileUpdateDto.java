package uz.pdp.fastfoodapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.fastfoodapp.util.annotations.Email;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class ProfileUpdateDto {
    private UUID id;
    private String name;
    @Email
    private String email;
}
