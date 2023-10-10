package com.dxc.backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class AuthResponseDto {
  private final String accessToken;
  private final String tokenType = "Bearer ";
  private final String username;
  private final String name;
  private final List<String> roles;

  public AuthResponseDto(String username, String name, String accessToken, List<String> roles) {
    this.accessToken = accessToken;
    this.username = username;
    this.name = name;
    this.roles = roles;
  }
}
