package uz.pdp.fastfoodapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class SuccessResponse {
    private String message;
    private final LocalDateTime timestamp = LocalDateTime.now();
}
