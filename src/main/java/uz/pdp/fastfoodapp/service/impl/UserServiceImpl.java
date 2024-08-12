package uz.pdp.fastfoodapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.fastfoodapp.dto.request.UserLoginDto;
import uz.pdp.fastfoodapp.dto.request.UserRegisterDto;
import uz.pdp.fastfoodapp.dto.response.JwtTokenDto;
import uz.pdp.fastfoodapp.dto.response.SuccessResponse;
import uz.pdp.fastfoodapp.exception.AlreadyExistsException;
import uz.pdp.fastfoodapp.exception.InvalidDataException;
import uz.pdp.fastfoodapp.exception.NotFoundException;
import uz.pdp.fastfoodapp.model.User;
import uz.pdp.fastfoodapp.repo.UserRepository;
import uz.pdp.fastfoodapp.security.jwt.JwtTokenProvider;
import uz.pdp.fastfoodapp.service.UserService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final EmailService emailService;

    @Override
    public SuccessResponse register(UserRegisterDto dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent())
            throw new AlreadyExistsException("User");
        emailService.send(dto.getEmail());
        userRepository.save(User.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build());
        return new SuccessResponse("Email sent");
    }

    @Override
    public JwtTokenDto checkEmail(String email, Integer code) {
        if (emailService.check(code, email)) {
            User user = userRepository.findByEmail(email).orElseThrow(
                    () -> new NotFoundException("User")
            );
            user.setVerified(true);
            return new JwtTokenDto(jwtTokenProvider.generateToken(userRepository.save(user)));
        }
        throw new InvalidDataException("verification code");
    }


    @Override
    public JwtTokenDto login(UserLoginDto dto) {
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(
                () -> new NotFoundException("User")
        );
        if (passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            return new JwtTokenDto(
                    jwtTokenProvider.generateToken(user)
            );
        }
        throw new InvalidDataException("password");
    }

    @Override
    public User getById(String id) {
        return userRepository.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("User")
        );
    }

    @Override
    public SuccessResponse forgotPassword(String email) {
        userRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("User")
        );
        emailService.send(email);
        return new SuccessResponse("Email sent");
    }

    @Override
    public SuccessResponse resetPassword(String email, Integer code, String newPassword) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("User")
        );
        if (emailService.check(code, email)) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return new SuccessResponse("Password reset successful");
        }
        else
            throw new InvalidDataException("code");
    }
}
