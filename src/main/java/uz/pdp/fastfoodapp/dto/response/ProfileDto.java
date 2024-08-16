package uz.pdp.fastfoodapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.fastfoodapp.model.User;
import uz.pdp.fastfoodapp.util.annotations.Email;

@AllArgsConstructor
@Getter
public class ProfileDto {
    private String name;
    @Email
    private String email;

    public ProfileDto(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
