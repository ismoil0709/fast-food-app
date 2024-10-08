package uz.pdp.fastfoodapp.service;

import org.springframework.stereotype.Service;
import uz.pdp.fastfoodapp.dto.response.ProfileDto;
import uz.pdp.fastfoodapp.dto.request.ProfileUpdateDto;
import uz.pdp.fastfoodapp.dto.request.UserLoginDto;
import uz.pdp.fastfoodapp.dto.response.JwtTokenDto;
import uz.pdp.fastfoodapp.dto.request.UserRegisterDto;
import uz.pdp.fastfoodapp.dto.response.SuccessResponse;
import uz.pdp.fastfoodapp.model.User;

import java.util.UUID;

@Service
public interface UserService {
    SuccessResponse register(UserRegisterDto dto);
    JwtTokenDto checkEmail(String email,Integer code);
    JwtTokenDto login(UserLoginDto dto);
    User getById(String id);
    SuccessResponse forgotPassword(String email);
    SuccessResponse resetPassword(String email, Integer code, String newPassword);
    ProfileDto getProfile(UUID id);
    ProfileDto updateProfile(ProfileUpdateDto dto);
}
