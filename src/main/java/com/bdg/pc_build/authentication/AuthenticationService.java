package com.bdg.pc_build.authentication;

import com.bdg.pc_build.config.JwtService;
import com.bdg.pc_build.exception.EmailAlreadyExistsException;
import com.bdg.pc_build.exception.UserNotFoundException;
import com.bdg.pc_build.token.Token;
import com.bdg.pc_build.token.TokenDAO;
import com.bdg.pc_build.token.TokenType;
import com.bdg.pc_build.user.enumerations.Role;
import com.bdg.pc_build.user.model.entity.User;
import com.bdg.pc_build.user.repository.UserDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final UserDAO userDAO;
    private final TokenDAO tokenDAO;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        Optional<User> optionalUser = userDAO.findByEmail(request.getEmail());
        if (optionalUser.isPresent()) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }
        var user = User.builder()
                .firstName(request.getFirstname().trim())
                .lastName(request.getLastname().trim())
                .email(request.getEmail().trim())
                .password(passwordEncoder.encode(request.getPassword().trim()))
                .role(Role.USER)
                .build();
        var savedUser = userDAO.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var optionalUser = userDAO.findByEmail(request.getEmail());
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(request.getEmail());
        }
        User user = optionalUser.get();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenDAO.save(token);
    }

    void revokeAllUserTokens(User user) {
        var validUserTokens = tokenDAO.findAllValidTokenByUserId(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenDAO.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.userDAO.findByEmail(userEmail)
                    .orElseThrow(() -> new UserNotFoundException(userEmail));
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}