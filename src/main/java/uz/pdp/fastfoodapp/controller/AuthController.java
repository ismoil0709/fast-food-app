package uz.pdp.fastfoodapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.fastfoodapp.dto.request.UserLoginDto;
import uz.pdp.fastfoodapp.dto.request.UserRegisterDto;
import uz.pdp.fastfoodapp.dto.request.ValidEmailDto;
import uz.pdp.fastfoodapp.service.UserService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDto dto) {
        return ResponseEntity.ok(userService.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto dto) {
        return ResponseEntity.ok(userService.login(dto));
    }

    @PostMapping("/check/email")
    public ResponseEntity<?> check(@RequestBody ValidEmailDto dto) {
        return ResponseEntity.ok(userService.checkEmail(dto.getEmail(), dto.getCode()));
    }
}
