package uz.pdp.fastfoodapp.security.jwt;

import io.jsonwebtoken.Claims;
import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.fastfoodapp.model.User;
import uz.pdp.fastfoodapp.service.UserService;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
@NonNullApi
public class JwtFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().startsWith("/api/v1/auth")){
            filterChain.doFilter(request,response);
            return;
        }
        String token = request.getHeader(AUTHORIZATION);
        if (token != null && token.startsWith(BEARER)) {
            token = token.split(" ")[1];
            if (jwtTokenProvider.isValid(token)) {
                Claims claims = jwtTokenProvider.parseAllClaims(token);
                if (claims.getSubject() != null) {
                    User user = userService.getById(claims.getSubject());
                    if (user != null){
                        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                                user.getId(),
                                null,
                                List.of(new SimpleGrantedAuthority("ROLE_USER"))
                        ));
                    }
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
