package com.bdg.pc_build.authentication;

import com.bdg.pc_build.config.JwtService;
import com.bdg.pc_build.exception.EmailAlreadyExistsException;
import com.bdg.pc_build.exception.UserNotFoundException;
import com.bdg.pc_build.token.Token;
import com.bdg.pc_build.token.TokenDAO;
import com.bdg.pc_build.user.enumerations.Role;
import com.bdg.pc_build.user.model.entity.User;
import com.bdg.pc_build.user.repository.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthenticationServiceTest {

    @Mock
    private UserDAO userDAO;
    @Mock
    private TokenDAO tokenDAO;
    @Mock
    private JwtService jwtService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;
    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister() {
        // Mock data
        RegisterRequest request = new RegisterRequest("John", "Doe", "john@example.com", "password");
        User savedUser = User.builder()
                .firstName(request.getFirstname().trim())
                .lastName(request.getLastname().trim())
                .email(request.getEmail().trim())
                .password("encoded_password")
                .role(Role.USER)
                .build();
        String jwtToken = "access_token";
        String refreshToken = "refresh_token";

        // Mocking behavior
        when(userDAO.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(userDAO.save(any(User.class))).thenReturn(savedUser);
        when(jwtService.generateToken(any(User.class))).thenReturn(jwtToken);
        when(jwtService.generateRefreshToken(any(User.class))).thenReturn(refreshToken);

        // Perform the registration
        AuthenticationResponse response = authenticationService.register(request);

        // Assertions
        assertNotNull(response);
        assertEquals(jwtToken, response.getAccessToken());
        assertEquals(refreshToken, response.getRefreshToken());
        verify(userDAO, times(1)).findByEmail(request.getEmail());
        verify(userDAO, times(1)).save(any(User.class));
        verify(jwtService, times(1)).generateToken(any(User.class));
        verify(jwtService, times(1)).generateRefreshToken(any(User.class));
        verify(tokenDAO, times(1)).save(any(Token.class));
    }

    @Test
    void testRegister_EmailAlreadyExistsException() {
        // Mock data
        RegisterRequest request = new RegisterRequest("John", "Doe", "john@example.com", "password");

        // Mocking behavior
        when(userDAO.findByEmail(request.getEmail())).thenReturn(Optional.of(new User()));

        // Perform the registration and assert that it throws EmailAlreadyExistsException
        assertThrows(EmailAlreadyExistsException.class, () -> authenticationService.register(request));

        // Verify interactions
        verify(userDAO, times(1)).findByEmail(request.getEmail());
        verify(userDAO, never()).save(any(User.class));
        verify(jwtService, never()).generateToken(any(User.class));
        verify(jwtService, never()).generateRefreshToken(any(User.class));
        verify(tokenDAO, never()).save(any(Token.class));
    }

    @Test
    void testAuthenticate() {
        // Mock data
        AuthenticationRequest request = new AuthenticationRequest("john@example.com", "password");
        User user = User.builder()
                .email(request.getEmail())
                .password("encoded_password")
                .build();
        String jwtToken = "access_token";
        String refreshToken = "refresh_token";

        // Mocking behavior
        when(userDAO.findByEmail(request.getEmail())).thenReturn(Optional.of(user));
        when(jwtService.generateToken(user)).thenReturn(jwtToken);
        when(jwtService.generateRefreshToken(user)).thenReturn(refreshToken);

        // Perform authentication
        AuthenticationResponse response = authenticationService.authenticate(request);

        // Assertions
        assertNotNull(response);
        assertEquals(jwtToken, response.getAccessToken());
        assertEquals(refreshToken, response.getRefreshToken());
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userDAO, times(1)).findByEmail(request.getEmail());
        verify(jwtService, times(1)).generateToken(user);
        verify(jwtService, times(1)).generateRefreshToken(user);
        verify(tokenDAO, times(1)).save(any(Token.class));
    }

    @Test
    void testAuthenticate_UserNotFoundException() {
        // Mock data
        AuthenticationRequest request = new AuthenticationRequest("john@example.com", "password");

        // Mocking behavior
        when(userDAO.findByEmail(request.getEmail())).thenReturn(Optional.empty());

        // Perform authentication and assert that it throws UserNotFoundException
        assertThrows(UserNotFoundException.class, () -> authenticationService.authenticate(request));

        // Verify interactions
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userDAO, times(1)).findByEmail(request.getEmail());
        verify(jwtService, never()).generateToken(any(User.class));
        verify(jwtService, never()).generateRefreshToken(any(User.class));
        verify(tokenDAO, never()).save(any(Token.class));
    }
}