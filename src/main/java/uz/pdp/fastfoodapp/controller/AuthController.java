package uz.pdp.fastfoodapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.fastfoodapp.dto.request.ChangePasswordDto;
import uz.pdp.fastfoodapp.dto.request.OAuthDto;
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

    @PostMapping("/oauth2/login/google")
    public ResponseEntity<?> handleOAuth2Login(@RequestBody OAuthDto dto) {
        System.out.println(dto);
        return ResponseEntity.ok("OAuth2 Login Successful");
    }

    @PutMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String email) {
        return ResponseEntity.ok(userService.forgotPassword(email));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> restPassword(@RequestBody ChangePasswordDto dto) {
        return ResponseEntity.ok(userService.resetPassword(dto.getEmail(), dto.getCode(), dto.getNewPassword()));
    }
}
