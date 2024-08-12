package uz.pdp.fastfoodapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OAuthDto {
    private String email;
    private String name;
}
