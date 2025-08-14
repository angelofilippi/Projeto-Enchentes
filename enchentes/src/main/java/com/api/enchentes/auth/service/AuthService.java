package com.api.enchentes.auth.service;

import com.api.enchentes.auth.dto.*;
import com.api.enchentes.security.JwtService;
import com.api.enchentes.user.entity.Role;
import com.api.enchentes.user.entity.User;
import com.api.enchentes.user.repository.RoleRepository;
import com.api.enchentes.user.repository.UserRepository;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authManager;
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public AuthService(AuthenticationManager authManager, UserRepository userRepo,
                       RoleRepository roleRepo, PasswordEncoder encoder, JwtService jwtService) {
        this.authManager = authManager;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.encoder = encoder;
        this.jwtService = jwtService;
    }

    public AuthResponse register(RegisterRequest req) {
        if (userRepo.existsByUsername(req.username())) {
            throw new IllegalArgumentException("Usuário já existe");
        }
        Role role = roleRepo.findByRolename("USUARIO")
                .orElseThrow(() -> new IllegalStateException("Role USUARIO não encontrada"));

        User u = new User();
        u.setUsername(req.username());
        u.setPassword(encoder.encode(req.password()));
        u.setRole(role);
        userRepo.save(u);

        String token = jwtService.generateToken(u.getUsername());
        return new AuthResponse(token);
    }

    public AuthResponse login(AuthRequest req) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(req.username(), req.password()));
        String token = jwtService.generateToken(req.username());
        return new AuthResponse(token);
    }
}
