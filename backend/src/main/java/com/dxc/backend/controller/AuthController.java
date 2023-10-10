package com.dxc.backend.controller;

import com.dxc.backend.dto.AuthResponseDto;
import com.dxc.backend.dto.LoginDto;
import com.dxc.backend.dto.RegisterDto;
import com.dxc.backend.model.Role;
import com.dxc.backend.model.UserEntity;
import com.dxc.backend.repository.RoleRepository;
import com.dxc.backend.repository.UserRepository;
import com.dxc.backend.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private AuthenticationManager authenticationManager;
  private UserRepository userRepository;
  private RoleRepository roleRepository;
  private PasswordEncoder passwordEncoder;
  private JwtUtils jwtUtils;

  @Autowired
  public AuthController(
      AuthenticationManager authenticationManager,
      UserRepository userRepository,
      RoleRepository roleRepository,
      PasswordEncoder passwordEncoder,
      JwtUtils jwtUtils
  ) {
    this.authenticationManager = authenticationManager;
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtUtils = jwtUtils;
  }

  @PostMapping("login")
  public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
    try {
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              loginDto.getUsername(),
              loginDto.getPassword()
          )
      );

      List<String> authorities = new ArrayList<>();
      for (GrantedAuthority authority : authentication.getAuthorities()) {
        authorities.add(authority.getAuthority());
      }

      SecurityContextHolder.getContext().setAuthentication(authentication);
      String token = jwtUtils.generateToken(authentication);
      UserEntity user = userRepository.findByUsername(loginDto.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
      return new ResponseEntity<>(new AuthResponseDto(loginDto.getUsername(), user.getName(), token, authorities), HttpStatus.OK);
    } catch (Exception e) {
      System.out.println("Invalid username or password " + e.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
  }

  @PostMapping("register")
  public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterDto registerDTO) {
    if (userRepository.existsByUsername(registerDTO.getUsername())) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    UserEntity user = new UserEntity();
    user.setUsername(registerDTO.getUsername());
    user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
    user.setName(registerDTO.getName());
    Role roles = roleRepository.findByName(registerDTO.getRole()).isPresent() ? roleRepository.findByName(registerDTO.getRole()).get() : null;
    user.setRoles(Collections.singletonList(roles));

    userRepository.save(user);

    return new ResponseEntity<>(null, HttpStatus.CREATED);
  }
}
