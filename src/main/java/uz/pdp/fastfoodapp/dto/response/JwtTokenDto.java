package uz.pdp.fastfoodapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class JwtTokenDto {
    private String token;
    private final LocalDateTime timestamp = LocalDateTime.now();
}
